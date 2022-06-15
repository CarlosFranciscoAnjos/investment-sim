package org.investmentsimspring.domain.liabilities.loans;

/**
 * @author Carlos Anjos
 * <p>
 * Utility class for loan calculation
 */
public class LoansCalculator {

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return the monthly payment (PMT)
     */
    public static double calculateMonthlyPayment(double principal, double rate, int term) {
        double i = rate / 12;
        return principal * (i * Math.pow(1 + i, term)) /
                (Math.pow(1 + i, term) - 1);
    }

    /**
     * @param monthlyPayment (PMT)
     * @param rate           per year in the scale 0-1 (i)
     * @param term           in months (n)
     * @return the loan principal (PV)
     */
    public static int calculatePrincipal(double monthlyPayment, double rate, int term) {
        double i = rate / 12;
        return (int) Math.round((monthlyPayment / i) * (1 - (1 / Math.pow(1 + i, term))));
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return amortization made in given month
     */
    public static double calculateAmortization(double principal, double rate, int term, int current) {
        return 0;
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return total amount paid
     */
    public static double calculateTotalAmount(double principal, double rate, int term) {
        return principal + calculateTotalInterest(principal, rate, term);
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @param current   month
     * @return interest paid in given month
     */
    public static double calculateInterest(double principal, double rate, int term, int current) {
        return 0;
    }

    /**
     * @param principal of the loan (PV)
     * @param rate      per year in the scale 0-1 (i)
     * @param term      in months (n)
     * @return total interest paid
     */
    public static double calculateTotalInterest(double principal, double rate, int term) {
        double i = rate / 12;
        double c = calculateMonthlyPayment(principal, rate, term);
        return (principal * i - c) * ((Math.pow(1 + i, term) - 1) / i) + (c * term);
    }

}
