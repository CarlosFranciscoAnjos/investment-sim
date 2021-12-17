package org.investmentsimspring.domain.concepts;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a rate
 */
public class Rate implements Serializable {

    // exception messages
    public static final String RATE_EXCEPTION = "Rate must be positive";

    private final double rate; // yearly rate

    /**
     * Initializes a rate with value 0
     */
    public Rate() {
        this.rate = 0;
    }

    /**
     * @param rate yearly rate in 0-1 scale
     */
    public Rate(double rate) {
        if (rate < 0)
            throw new IllegalArgumentException(RATE_EXCEPTION);
        this.rate = rate;
    }

    /**
     * @param value  total asset value
     * @param income asset monthly income
     */
    public Rate(Value value, Income income) {
        this.rate = (income.getValue() / value.getValue()) * 12;
    }

    /**
     * @param spread
     * @param euribor
     */
    public Rate(Spread spread, Euribor euribor) {
        this.rate = spread.getValue() + euribor.getValue();
    }

    /**
     * @return monthly rate in 0-1 scale
     */
    public double monthlyRate() {
        return rate / 12;
    }

    /**
     * @return yearly rate in 0-1 scale
     */
    public double getValue() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate1 = (Rate) o;
        return Double.compare(rate1.rate, rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }

    @Override
    public String toString() {
        return String.valueOf(rate);
    }
}
