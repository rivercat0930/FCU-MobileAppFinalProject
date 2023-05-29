package dev.rivercat.fw_courier.module;

import androidx.annotation.NonNull;

public class RestaurantInformation {
    private String restaurantName;
    private Double rate;
    private String description;
    private String account;
    private String password;


    public RestaurantInformation(String restaurantName, String account, String password) {
        this.restaurantName = restaurantName;
        this.rate = 4.5;
        this.description = "nice的餐廳";
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

    @NonNull
    @Override
    public String toString() {
      return "RegisterInformation{" +
        "restaurantName='"+restaurantName+'\''+
        ",ratr="+rate+
        ",description='"+description+'\''+
        '}';
    }
}
