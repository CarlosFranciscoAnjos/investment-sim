package org.investmentsimspring.domain.simulations;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.contracts.Builder;
import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.domain.users.UserBuilder;
import org.investmentsimspring.models.simulations.SimulationDto;
import org.investmentsimspring.models.users.UserDto;

public class SimulationBuilder implements Builder<Simulation> {

    public static Simulation build(SimulationDto dto) {
        SimulationBuilder builder = new SimulationBuilder();
        builder.id(dto.id).user(dto.user).description(dto.description);
        return builder.build();
    }

    private long id;
    private User user;
    private Description description;

    public SimulationBuilder() {
        this.id = 0;
    }

    public SimulationBuilder id(long id) {
        this.id = id;
        return this;
    }

    public SimulationBuilder user(User user) {
        this.user = user;
        return this;
    }

    public SimulationBuilder user(UserDto dto) {
        this.user = UserBuilder.build(dto);
        return this;
    }

    public SimulationBuilder description(String description) {
        this.description = new Description(description);
        return this;
    }

    @Override
    public Simulation build() {
        return id <= 0 ? new Simulation(description, user)
                : new Simulation(id, description, user);
    }
}
