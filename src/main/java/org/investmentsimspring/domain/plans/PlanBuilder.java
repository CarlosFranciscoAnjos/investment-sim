package org.investmentsimspring.domain.plans;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.containers.Container;
import org.investmentsimspring.domain.containers.ContainerDto;
import org.investmentsimspring.domain.contracts.Builder;
import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.domain.users.UserBuilder;
import org.investmentsimspring.domain.users.UserDto;

public class PlanBuilder implements Builder<Plan> {

    public static Plan build(PlanDto dto) {
        PlanBuilder builder = new PlanBuilder();
        builder.id(dto.id).user(dto.user).description(dto.description).container(dto.containerDto);
        return builder.build();
    }

    private long id;
    private User user;
    private Description description;
    private Container container;

    public PlanBuilder() {
        this.id = 0;
    }

    public PlanBuilder id(long id) {
        this.id = id;
        return this;
    }

    public PlanBuilder user(User user) {
        this.user = user;
        return this;
    }

    public PlanBuilder user(UserDto dto) {
        this.user = UserBuilder.build(dto);
        return this;
    }

    public PlanBuilder description(String description) {
        this.description = new Description(description);
        return this;
    }

    public PlanBuilder container(Container container) {
        this.container = container;
        return this;
    }

    public PlanBuilder container(ContainerDto dto) {
        User user = UserBuilder.build(dto.userDto);
        this.container = new Container(dto.id, user);
        return this;
    }

    @Override
    public Plan build() {
        return id <= 0 ? new Plan(description, user)
                : new Plan(id, description, user, container);
    }
}