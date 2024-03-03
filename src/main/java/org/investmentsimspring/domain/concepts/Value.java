package org.investmentsimspring.domain.concepts;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the total value of an asset
 */
@Embeddable
public class Value implements Serializable {

    // exception messages
    public static final String VALUE_EXCEPTION = "Value must be greater than zero";

    @Access(AccessType.FIELD)
    private final double value;

    protected Value() {
        this.value = 0;
    }

    /**
     * @param value the total value
     */
    public Value(double value) {
        if (value <= 0)
            throw new IllegalArgumentException(VALUE_EXCEPTION);
        this.value = value;
    }

    /**
     * @return value
     */
    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return Double.compare(value1.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
