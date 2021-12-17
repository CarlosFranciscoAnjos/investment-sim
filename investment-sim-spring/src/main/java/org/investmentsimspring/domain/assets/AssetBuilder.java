package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.contracts.Builder;
import org.investmentsimspring.domain.plans.Plan;
import org.investmentsimspring.domain.plans.PlanBuilder;
import org.investmentsimspring.domain.plans.PlanDto;

public class AssetBuilder implements Builder<Asset> {

    private long id;
    private Plan plan;
    private Description description;
    private Income income;
    private Value value;

    public AssetBuilder() {
        this.id = 0;
    }

    public AssetBuilder id(long id) {
        this.id = id;
        return this;
    }

    public AssetBuilder plan(Plan plan) {
        this.plan = plan;
        return this;
    }

    public AssetBuilder plan(PlanDto dto) {
        this.plan = PlanBuilder.build(dto);
        return this;
    }

    public AssetBuilder description(String description) {
        this.description = new Description(description);
        return this;
    }

    public AssetBuilder income(double income) {
        this.income = new Income(income);
        return this;
    }

    public AssetBuilder value(double value) {
        this.value = new Value(value);
        return this;
    }

    @Override
    public Asset build() {
        return id <= 0 ? new Asset(description, income, value, plan) :
                new Asset(id, description, income, value, plan);
    }
}
