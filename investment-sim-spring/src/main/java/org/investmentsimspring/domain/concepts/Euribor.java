package org.investmentsimspring.domain.concepts;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents Euribor index (used in loans)
 */
public class Euribor implements Serializable {

    // exception messages
    public static final String EURIBOR_EXCEPTION = "Euribor must be positive";

    public static final double EURIBOR = 0.02;

    private final double euribor;

    /**
     * Initializes euribor at default value
     */
    public Euribor() {
        this.euribor = EURIBOR;
    }

    /**
     * @param euribor the euribor value
     */
    public Euribor(double euribor) {
        if (euribor < 0)
            throw new IllegalArgumentException(EURIBOR_EXCEPTION);
        this.euribor = euribor;
    }

    /**
     * @return euribor
     */
    public double getValue() {
        return euribor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Euribor euribor1 = (Euribor) o;
        return Double.compare(euribor1.euribor, euribor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(euribor);
    }

    @Override
    public String toString() {
        return String.valueOf(euribor);
    }
}
