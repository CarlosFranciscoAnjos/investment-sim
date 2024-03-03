package org.investmentsimspring.domain.assets.estates;

import org.investmentsimspring.domain.assets.Asset;
import org.investmentsimspring.domain.concepts.Address;
import org.investmentsimspring.domain.concepts.Description;
import org.investmentsimspring.domain.concepts.Income;
import org.investmentsimspring.domain.concepts.Value;
import org.investmentsimspring.domain.containers.Container;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

/**
 * @author Carlos Anjos
 * <p>
 * Represents a Real Estate Asset
 */
@Entity
public class Estate extends Asset {

    @Enumerated
    private PropertyType type;
    @Embedded
    private Address address;
    @Embedded
    private Area area;

    protected Estate() {
        super();
    }

    public Estate(Description description, Income income, Value value, Container container,
                  PropertyType type, Address address, Area area) {
        super(description, income, value, container);
        this.address = address;
        this.type = type;
        this.area = area;
    }

    //region getters
    public Address getAddress() {
        return address;
    }

    public PropertyType getType() {
        return type;
    }

    public Area getArea() {
        return area;
    }

    //endregion

    @Override
    public String toString() {
        return "Estate{" +
                "type=" + type +
                ", address=" + address +
                '}';
    }

    @Override
    public EstateDto toDto() {
        return (EstateDto) super.toDto();
    }
}
