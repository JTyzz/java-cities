package com.jasontyzzer.javacities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class City {
    private @Id
    @GeneratedValue
    long id;
    private String city;
    private int averageHouseCost;
    private int affordabilityIndex;

    public City() {
    }

    public City(String city, int averageHouseCost, int affordabilityIndex) {
        this.city = city;
        this.averageHouseCost = averageHouseCost;
        this.affordabilityIndex = affordabilityIndex;
    }
}