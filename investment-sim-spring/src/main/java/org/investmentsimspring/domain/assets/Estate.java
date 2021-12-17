package org.investmentsimspring.domain.assets;

import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.plans.Plan;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a Real Estate Asset
 */
public class Estate extends Asset {

    private Coordinates coordinates;
    private PropertyType type;

    public Estate(Description description, Income income, Value value, Plan plan,
                  Coordinates coordinates, PropertyType type) {
        super(description, income, value, plan);
        this.coordinates = coordinates;
        this.type = type;
    }


    //region getters
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public PropertyType getType() {
        return type;
    }
    //endregion


    @Override
    public String toString() {
        return "Estate{" +
                "id=" + id +
                ", description=" + description +
                ", income=" + income +
                ", value=" + value +
                ", plan=" + plan +
                ", coordinates=" + coordinates +
                ", type=" + type +
                '}';
    }

}
