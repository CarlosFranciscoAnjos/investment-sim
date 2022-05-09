package org.investmentsimspring.domain.concepts;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents revenue
 */
@Embeddable
public class Income implements Serializable {

    // exception messages
    public static final String INCOME_EXCEPTION = "Income must be positive";

    @Access(AccessType.FIELD)
    private final double income;

    /**
     * Initializes a income with income 0
     */
    protected Income() {
        this.income = 0;
    }

    /**
     * @param income the monthly revenue
     */
    public Income(double income) {
        if (income < 0)
            throw new IllegalArgumentException(INCOME_EXCEPTION);
        this.income = income;
    }

    /**
     * @return monthly income
     */
    public double getValue() {
        return income;
    }

    /**
     * @return yearly income
     */
    public double yearlyIncome() {
        return income * 12;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Double.compare(income.income, this.income) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(income);
    }

    @Override
    public String toString() {
        return String.valueOf(income);
    }
}
