package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.simulations.Simulation;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.contracts.Item;
import org.investmentsimspring.domain.contracts.Valuable;
import org.investmentsimspring.models.assets.AssetDto;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents an Asset
 */
@Entity
@Table(name = "assets")
@Inheritance(strategy = InheritanceType.JOINED)
public class Asset implements Item, Valuable, Dtoable<AssetDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Embedded
    protected Description description;
    @Embedded
    protected Income income;
    @Embedded
    protected Value value;
//    @OneToMany(mappedBy = "asset")
//    protected List<Liability> liabilities;
    @ManyToOne
    protected Simulation simulation;

    protected Asset() {
    }

    protected Asset(long id, Description description, Income income, Value value, Simulation simulation) {
        this.id = id;
        this.description = description;
        this.income = income;
        this.value = value;
        this.simulation = simulation;
    }

    public Asset(Description description, Income income, Value value, Simulation simulation) {
        this.description = description;
        this.income = income;
        this.value = value;
        this.simulation = simulation;
    }

    //region getters & setters

    public long getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }

    public Income getIncome() {
        return income;
    }

    public Value getValue() {
        return value;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    //endregion

    /**
     * @return monthly rate (0-1 scale)
     */
    public double calculateMonthlyRate() {
        return income.getValue() / value.getValue();
    }

    /**
     * @return yearly rate (0-1 scale)
     */
    public double calculateYearlyRate() {
        return calculateMonthlyRate() * 12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset asset)) return false;
        return id == asset.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", description=" + description +
                ", income=" + income +
                ", value=" + value +
                ", container=" + simulation +
                '}';
    }

    @Override
    public AssetDto toDto() {
        AssetDto dto = new AssetDto();
        dto.id = this.id;
        dto.simulationDto = this.simulation.toDto();
        dto.description = this.description.getValue();
        dto.income = this.income.getValue();
        dto.value = this.value.getValue();
        dto.rate = calculateYearlyRate();
        return dto;
    }

    @Override
    public double flow() {
        return income.getValue();
    }

    @Override
    public double value() {
        return value.getValue();
    }
}
