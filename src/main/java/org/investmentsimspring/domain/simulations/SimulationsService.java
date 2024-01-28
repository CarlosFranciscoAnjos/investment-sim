package org.investmentsimspring.domain.simulations;

import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.infrastructure.SimulationsRepository;
import org.investmentsimspring.infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulationsService {

    private final SimulationsRepository simulationsRepo;
    private final UsersRepository usersRepo;

    @Autowired
    public SimulationsService(SimulationsRepository simulationsRepo, UsersRepository usersRepo) {
        this.simulationsRepo = simulationsRepo;
        this.usersRepo = usersRepo;
    }

    public List<SimulationDto> getAllSimulations() {
        return simulationsRepo.findAll().stream()
                .map(Simulation::toDto)
                .collect(Collectors.toList());
    }

    public SimulationDto getSimulation(long id) {
        Simulation simulation = simulationsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Simulation with id %d not found", id)));
        return simulation.toDto();
    }

    public List<SimulationDto> getSimulationsByUserId(long userId) {
        List<Simulation> simulations = simulationsRepo.findAllByUserId(userId);
        return simulations.stream().map(Simulation::toDto).toList();
    }

    public SimulationDto createSimulation(CreateSimulationDto dto) {
        // fetch user
        User user = usersRepo.findById(dto.userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id %d not found", dto.userId)));
        // simulation builder
        SimulationBuilder builder = new SimulationBuilder();
        builder.description(dto.description);
        builder.user(user);
        // save in repo
        Simulation simulation = simulationsRepo.save(builder.build());
        return simulation.toDto();
    }

    public SimulationDto deleteSimulation(long id) {
        Simulation simulation = simulationsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Simulation with id %d not found", id)));
        simulationsRepo.delete(simulation);
        return simulation.toDto();
    }
}
