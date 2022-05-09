package org.investmentsimspring.domain.concepts;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents spread (commission on loans)
 */
@Embeddable
public class Spread implements Serializable {

    // exception messages
    public static final String SPREAD_EXCEPTION = "Spread must be positive";

    @Access(AccessType.FIELD)
    private final double spread;

    protected Spread() {
        this.spread = 0;
    }

    /**
     * @param spread monthly in 0-1 scale
     */
    public Spread(double spread) {
        if (spread < 0)
            throw new IllegalArgumentException(SPREAD_EXCEPTION);
        this.spread = spread;
    }

    /**
     * @return monthly in 0-1 scale
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
