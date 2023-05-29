package dev.rivercat.fw_courier.module;

public class RestaurantInformation {
    String restaurantName;
    String account;
    String password;

    public RestaurantInformation(String restaurantName, String account, String password) {
        this.restaurantName = restaurantName;
        this.account = account;
        this.password = password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
