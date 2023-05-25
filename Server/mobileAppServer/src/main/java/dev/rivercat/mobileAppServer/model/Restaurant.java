package dev.rivercat.mobileAppServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "restaurant_name")
    String restaurantName;

    @Column(name = "rate")
    Double rate;

    @Column(name = "description")
    String description;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, Double rate, String description) {
        this.restaurantName = restaurantName;
        this.rate = rate;
        this.description = description;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
