package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.contracts.Builder;
import org.investmentsimspring.domain.containers.Container;

public class AssetBuilder implements Builder<Asset> {

    private long id;
    private Container container;
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

    public AssetBuilder container(Container containerId) {
        this.container = containerId;
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
        return id <= 0 ? new Asset(description, income, value, container) :
                new Asset(id, description, income, value, container);
    }
}
