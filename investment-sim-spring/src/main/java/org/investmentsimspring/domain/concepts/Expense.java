package org.investmentsimspring.domain.concepts;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a cost
 */
@Embeddable
public class Expense implements Serializable {

    // exception messages
    public static final String COST_EXCEPTION = "Expense must be positive";

    @Access(AccessType.FIELD)
    private final double expense;

    /**
     * Initializes an expense with value 0
     */
    protected Expense() {
        this.expense = 0;
    }

    /**
     * @param expense the monthly cost
     */
    public Expense(double expense) {
        if (expense < 0)
            throw new IllegalArgumentException(COST_EXCEPTION);
        this.expense = expense;
    }

    /**
     * @return monthly cost
     */
    public double getValue() {
        return expense;
    }

    /**
     * @return yearly cost
     */
    public double yearlyCost() {
        return expense * 12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense1 = (Expense) o;
        return Double.compare(expense1.expense, expense) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expense);
    }

    @Override
    public String toString() {
        return String.valueOf(expense);
    }
}
