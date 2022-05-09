package org.investmentsimspring.domain.liabilities;

import org.investmentsimspring.domain.assets.Asset;
import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Expense;
import org.investmentsimspring.domain.contracts.Builder;

/**
 * @author Carlos Anjos
 */
public class LiabilityBuilder implements Builder<Liability> {

    private long id;
    private Description description;
    private Expense expense;
    private Asset asset;

    public LiabilityBuilder id(long id) {
        this.id = id;
        return this;
    }

    public LiabilityBuilder description(String description) {
        this.description = new Description(description);
        return this;
    }

    public LiabilityBuilder expense(double expense) {
        this.expense = new Expense(expense);
        return this;
    }

    public LiabilityBuilder asset(Asset asset) {
        this.asset = asset;
        return this;
    }

    @Override
    public Liability build() {
        return id <= 0 ? new Liability(description, expense, asset) :
                new Liability(id, description, expense, asset);
    }

}
