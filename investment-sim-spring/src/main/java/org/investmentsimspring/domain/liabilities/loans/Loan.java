package org.investmentsimspring.domain.liabilities.loans;

import org.investmentsimspring.domain.concepts.Rate;
import org.investmentsimspring.domain.concepts.Term;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.contracts.Valuable;
import org.investmentsimspring.domain.liabilities.Liability;

import javax.persistence.Embedded;

public class Loan extends Liability implements Valuable {

    /**
     * @param amount of the loan (PV)
     * @param rate   per year in the scale 0-1 (i)
     * @param term   in months (n)
     * @return the monthly payment
     */
    public static double calculateMonthlyPayment(double amount, double rate, int term) {
        double i = rate / 12;
        double nomerator = amount * i * Math.pow(1 + i, term);
        double denominator = Math.pow(1 + i, term) - 1;
        return nomerator / denominator;
    }

    /**
     * @param monthlyPayment (PMT)
     * @param rate           per year in the scale 0-1 (i)
     * @param term           in months (n)
     * @return the total loan amount
     */
    public static double calculateLoanAmount(double monthlyPayment, double rate, int term) {
        return 0;
    }

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
