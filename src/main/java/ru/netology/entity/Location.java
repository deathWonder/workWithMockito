package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int building;

    public Location(String city, Country country, String street, int building) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return building;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if ((this.getCity() == null) ? (other.getCity() != null) : !this.getCity().equals(other.getCity())) {
            return false;
        }
        if ((this.getCountry() == null) ? (other.getCountry() != null) : !(this.getCountry() == other.getCountry())) {
            return false;
        }
        if ((this.getStreet() == null) ? (other.getStreet() != null) : !this.getStreet().equals(other.getStreet())) {
            return false;
        }
        if (this.getBuiling() != other.getBuiling()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.getCity() != null ? this.getCity().hashCode() : 0);
        hash = 53 * hash + this.getBuiling();
        return hash;
    }

}
