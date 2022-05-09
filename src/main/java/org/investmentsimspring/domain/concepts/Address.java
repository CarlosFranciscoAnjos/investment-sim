package org.investmentsimspring.domain.concepts;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    @Access(AccessType.FIELD)
    private final String door;
    @Access(AccessType.FIELD)
    private final String street;
    @Access(AccessType.FIELD)
    private final String locality;
    @Access(AccessType.FIELD)
    private final String district;
    @Access(AccessType.FIELD)
    private final String country;

    protected Address() {
        door = "";
        street = "";
        locality = "";
        district = "";
        country = "";
    }

    public Address(String door,
                   String street,
                   String locality,
                   String district,
                   String country) {
        this.door = door;
        this.street = street;
        this.locality = locality;
        this.district = district;
        this.country = country;
    }

    public String getDoor() {
        return door;
    }

    public String getStreet() {
        return street;
    }

    public String getLocality() {
        return locality;
    }

    public String getDistrict() {
        return district;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(door, address.door) && Objects.equals(street, address.street) && Objects.equals(locality, address.locality) && Objects.equals(district, address.district) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(door, street, locality, district, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "door='" + door + '\'' +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", district='" + district + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
