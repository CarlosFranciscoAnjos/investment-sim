package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.containers.Container;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.contracts.Item;
import org.investmentsimspring.domain.contracts.Valuable;
import org.investmentsimspring.domain.liabilities.Liability;

import javax.persistence.*;
import java.util.List;
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
    protected Container container;

    protected Asset() {
    }

    protected Asset(long id, Description description, Income income, Value value, Container container) {
        this.id = id;
        this.description = description;
        this.income = income;
        this.value = value;
        this.container = container;
    }

    public Asset(Description description, Income income, Value value) {
        this.description = description;
        this.income = income;
        this.value = value;
    }

    public Asset(Description description, Income income, Value value, Container container) {
        this.description = description;
        this.income = income;
        this.value = value;
        this.container = container;
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

    public Container getContainer() {
        return container;
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
                ", container=" + container +
                '}';
    }

    @Override
    public AssetDto toDto() {
        AssetDto dto = new AssetDto();
        dto.id = this.id;
        dto.containerDto = this.container.toDto();
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
