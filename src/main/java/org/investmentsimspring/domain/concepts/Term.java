package org.investmentsimspring.domain.concepts;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Carlos Anjos
 * <p>
 * Represents the duration of something
 */
public class Term implements Serializable {

    // exception messages
    public static final String TERM_EXCEPTION = "Term must be greater than zero";
    private static final String TERM_BOUNDS_EXCEPTION = "Value out of term bounds";

    private final int months;

    /**
     * @param months the duration in months
     */
    public Term(int months) {
        if (months <= 0)
            throw new IllegalArgumentException(TERM_EXCEPTION);
        this.months = months;
    }

    /**
     * @return months
     */
    public int getValue() {
        return months;
    }

    /**
     * @param currentMonth months since the beginning
     * @return months left
     */
    public int monthsLeft(int currentMonth) {
        if (!isMonthWithinBounds(currentMonth))
            throw new IllegalArgumentException(TERM_BOUNDS_EXCEPTION);
        return months - currentMonth;
    }

    /**
     * @return years
     */
    public double years() {
        return (double) months / 12;
    }

    /**
     * @param currentYear years since the beginning
     * @return years left
     */
    public double yearsLeft(double currentYear) {
        if (!isYearWithinBounds(currentYear))
            throw new IllegalArgumentException(TERM_BOUNDS_EXCEPTION);
        return years() - currentYear;
    }

    /**
     * @param month to verify
     * @return true if the given month is within bounds, false otherwise
     */
    public boolean isMonthWithinBounds(int month) {
        return month >= 0 && month <= getValue();
    }

    /**
     * @param year to verify
     * @return true if the given year is within bounds, false otherwise
     */
    public boolean isYearWithinBounds(double year) {
        return year >= 0 && year <= years();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return months == term.months;
    }

    @Override
    public int hashCode() {
        return Objects.hash(months);
    }

    @Override
    public String toString() {
        return String.format("%d months", months);
    }
}
