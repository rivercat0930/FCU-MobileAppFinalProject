package dev.rivercat.fw_courier.module;

import androidx.annotation.NonNull;

public class RestaurantInformation {
  public class RegisterInformation {

    private String restaurantName;
    private Double rate;
    private String description;



    public RegisterInformation(String restaurantName, Double rate, String description) {
      this.restaurantName = restaurantName;
      this.rate = rate;
      this.description = description;

    }

    public String getRestaurantName() {
      return restaurantName;
    }

    public Double getRate() {
      return rate;
    }

    public void setRestaurantName(String restaurantName) {
      this.restaurantName = restaurantName;
    }

    public void setRate(Double rate) {
      this.rate = rate;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getDescription()
        return description;

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
}
