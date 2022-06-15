package org.investmentsimspring.domain.liabilities.loans;

import org.investmentsimspring.domain.concepts.Rate;
import org.investmentsimspring.domain.concepts.Term;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.contracts.Valuable;
import org.investmentsimspring.domain.liabilities.Liability;

import javax.persistence.Embedded;

public class Loan extends Liability implements Valuable {

    @Embedded
    protected Value value;
    @Embedded
    protected Term term;
    @Embedded
    protected Rate rate;

    @Override
    public double value() {
        return 0;
    }
}
