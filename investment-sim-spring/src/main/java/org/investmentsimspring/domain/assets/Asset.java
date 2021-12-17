package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Rate;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.plans.Plan;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents an Asset
 */
@Entity
public class Asset implements Dtoable<AssetDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Embedded
    protected Description description;
    @Embedded
    protected Income income;
    @Embedded
    protected Value value;
    @ManyToOne
    protected Plan plan;

    protected Asset() {
    }

    protected Asset(long id, Description description, Income income, Value value, Plan plan) {
        this.id = id;
        this.description = description;
        this.income = income;
        this.value = value;
        this.plan = plan;
    }

    public Asset(Description description, Income income, Value value) {
        this.description = description;
        this.income = income;
        this.value = value;
    }

    public Asset(Description description, Income income, Value value, Plan plan) {
        this.description = description;
        this.income = income;
        this.value = value;
        this.plan = plan;
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

    public Plan getPlan() {
        return plan;
    }

    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset asset = (Asset) o;
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
                '}';
    }

    @Override
    public AssetDto toDto() {
        AssetDto dto = new AssetDto();
        dto.id = this.id;
        dto.description = this.description.getValue();
        dto.income = this.income.getValue();
        dto.value = this.value.getValue();
        dto.rate = new Rate(value, income).monthlyRate();
        return dto;
    }

}
