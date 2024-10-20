package org.investmentsimspring.domain.liabilities;

import org.investmentsimspring.domain.assets.Asset;
import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Expense;
import org.investmentsimspring.domain.contracts.Dtoable;
import org.investmentsimspring.domain.contracts.Item;
import org.investmentsimspring.models.liabilities.LiabilityDto;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "liabilities")
@Inheritance(strategy = InheritanceType.JOINED)
public class Liability implements Item, Dtoable<LiabilityDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Embedded
    protected Description description;
    @Embedded
    protected Expense expense;
    @ManyToOne
    protected Asset asset;

    protected Liability() {
    }

    protected Liability(long id, Description description, Expense expense, Asset asset) {
        this.id = id;
        this.description = description;
        this.expense = expense;
        this.asset = asset;
    }

    public Liability(Description description, Expense expense, Asset asset) {
        this.description = description;
        this.expense = expense;
        this.asset = asset;
    }

    //region getters & setters
    public long getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }

    public Expense getExpense() {
        return expense;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Liability liability)) return false;
        return id == liability.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Liability{" +
                "id=" + id +
                ", description=" + description +
                ", expense=" + expense +
                '}';
    }

    @Override
    public LiabilityDto toDto() {
        LiabilityDto dto = new LiabilityDto();
        dto.id = this.id;
        dto.description = this.description.getValue();
        dto.expense = this.expense.getValue();
        dto.assetDto = this.asset.toDto();
        return dto;
    }

    @Override
    public double flow() {
        return -expense.getValue();
    }
}
