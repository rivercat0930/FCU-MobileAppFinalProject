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

    @Column(name = "account")
    String account;

    @Column(name = "password")
    String password;

    public Restaurant() {
        this.rate = 4.5;
        this.description = "Nice的餐廳";
    }

    public Restaurant(String restaurantName, String account, String password) {
        this.restaurantName = restaurantName;
        this.rate = 4.5;
        this.description = "Nice的餐廳";
        this.account = account;
        this.password = password;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
