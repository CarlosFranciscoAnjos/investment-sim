package org.investmentsimspring.domain.simulations;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.users.User;
import org.investmentsimspring.models.simulations.SimulationDto;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a business Simulation
 */
@Entity
@Table(name = "simulations")
public class Simulation implements Dtoable<SimulationDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Embedded
    private Description description;
    @ManyToOne
    private User user;

    protected Simulation() {
    }

    protected Simulation(long id, Description description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Simulation(Description description, User user) {
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
        Simulation simulation = (Simulation) o;
        return id == simulation.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "id=" + id +
                ", user=" + user +
                ", description=" + description +
                '}';
    }

    public SimulationDto toDto() {
        SimulationDto dto = new SimulationDto();
        dto.id = this.id;
        dto.user = this.user.toDto();
        dto.description = this.description.getValue();
        return dto;
    }
}
