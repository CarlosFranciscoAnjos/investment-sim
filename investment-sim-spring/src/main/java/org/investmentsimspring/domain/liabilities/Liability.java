package org.investmentsimspring.domain.liabilities;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Expense;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Liability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Embedded
    protected Description description;
    @Embedded
    protected Expense expense;

    protected Liability() {
    }

    protected Liability(long id, Description description, Expense expense) {
        this.id = id;
        this.description = description;
        this.expense = expense;
    }

    public Liability(Description description, Expense expense) {
        this.description = description;
        this.expense = expense;
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
        if (!(o instanceof Liability)) return false;
        Liability liability = (Liability) o;
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
}
