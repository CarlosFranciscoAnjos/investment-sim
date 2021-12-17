package org.investmentsimspring.domain.plans;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.users.User;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a business Plan
 */
@Entity
public class Plan implements Dtoable<PlanDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Description description;
    @ManyToOne
    private User user;

    protected Plan() {
    }

    protected Plan(long id, Description description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Plan(Description description, User user) {
        this.description = description;
        this.user = user;
    }

    //region getters & setters
    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Description getDescription() {
        return description;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", user=" + user +
                ", description=" + description +
                '}';
    }

    public PlanDto toDto() {
        PlanDto dto = new PlanDto();
        dto.id = this.id;
        dto.user = this.user.toDto();
        dto.description = this.description.getValue();
        return dto;
    }
}
