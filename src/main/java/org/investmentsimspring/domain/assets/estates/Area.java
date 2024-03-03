package org.investmentsimspring.domain.assets.estates;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 * @author Carlos Anjos
 */
@Embeddable
public class Area {

    // exception messages
    public static final String AREA_EXCEPTION = "Area must be positive";

    @Access(AccessType.FIELD)
    private final double area;

    /**
     * Initializes an area with value 0
     */
    protected Area() {
        this.area = 0;
    }

    /**
     * @param area of the estate (square meters)
     */
    public Area(double area) {
        if (area < 0)
            throw new IllegalArgumentException(AREA_EXCEPTION);
        this.area = area;
    }

    /**
     * @return estate's area
     */
    public double getValue() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area1 = (Area) o;
        return Double.compare(area1.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }

    @Override
    public String toString() {
        return String.valueOf(area);
    }
}
