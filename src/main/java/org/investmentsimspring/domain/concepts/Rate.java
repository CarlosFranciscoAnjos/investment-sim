package org.investmentsimspring.domain.concepts;

import javax.persistence.Access;
import javax.persistence.AccessType;
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

    @Access(AccessType.FIELD)
    private final double rate; // monthly rate

    /**
     * Initializes a rate with value 0
     */
    public Rate() {
        this.rate = 0;
    }

    /**
     * @param rate monthly rate in 0-1 scale
     */
    public Rate(double rate) {
        if (rate < 0)
            throw new IllegalArgumentException(RATE_EXCEPTION);
        this.rate = rate;
    }

    /**
     * @return monthly rate in 0-1 scale
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
