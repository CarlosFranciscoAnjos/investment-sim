package org.investmentsimspring.domain.concepts;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents spread (commission on loans)
 */
public class Spread implements Serializable {

    // exception messages
    public static final String SPREAD_EXCEPTION = "Spread must be positive";

    private final double spread;

    /**
     * @param spread the spread value
     */
    public Spread(double spread) {
        if (spread < 0)
            throw new IllegalArgumentException(SPREAD_EXCEPTION);
        this.spread = spread;
    }

    /**
     * @return spread
     */
    public double getValue() {
        return spread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spread spread1 = (Spread) o;
        return Double.compare(spread1.spread, spread) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spread);
    }

    @Override
    public String toString() {
        return String.valueOf(spread);
    }
}
