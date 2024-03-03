package org.investmentsimspring.domain.concepts;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Description implements Serializable {

    // exception messages
    public static final String INVALID_DESCRIPTION_EXCEPTION = "Description can not be null nor empty";

    public static final int MAX_DESCRIPTION_LENGTH = 255;

    @Access(AccessType.FIELD)
    private final String description;

    protected Description() {
        this.description = "";
    }

    public Description(String description) {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException(INVALID_DESCRIPTION_EXCEPTION);
        if (description.length() > MAX_DESCRIPTION_LENGTH)
            throw new IllegalArgumentException(INVALID_DESCRIPTION_EXCEPTION);
        this.description = description;
    }

    /**
     * @return description
     */
    public String getValue() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
