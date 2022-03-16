package org.investmentsimspring.domain.plans;

import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.infrastructure.PlansRepository;
import org.investmentsimspring.infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlansService {

    private final PlansRepository plansRepo;
    private final UsersRepository usersRepo;

    @Autowired
    public PlansService(PlansRepository plansRepo, UsersRepository usersRepo) {
        this.plansRepo = plansRepo;
        this.usersRepo = usersRepo;
    }

    public List<PlanDto> getAllPlans() {
        return plansRepo.findAll().stream()
                .map(Plan::toDto)
                .collect(Collectors.toList());
    }

    public PlanDto getPlan(long id) {
        Plan plan = plansRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Plan with id %d not found", id)));
        return plan.toDto();
    }

    public List<PlanDto> getPlansByUserId(long userId) {
        List<Plan> plans = plansRepo.findAllByUserId(userId);
        return plans.stream().map(Plan::toDto).toList();
    }

    public PlanDto createPlan(CreatePlanDto dto) {
        // fetch user
        User user = usersRepo.findById(dto.userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id %d not found", dto.userId)));
        // plan builder
        PlanBuilder builder = new PlanBuilder();
        builder.description(dto.description);
        builder.user(user);
        // save in repo
        Plan plan = plansRepo.save(builder.build());
        return plan.toDto();
    }

    public PlanDto deletePlan(long id) {
        Plan plan = plansRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Plan with id %d not found", id)));
        plansRepo.delete(plan);
        return plan.toDto();
    }
}
