package dev.rivercat.mobileAppServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurantFood")
public class RestaurantFood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "restaurantName")
    private String restaurantName;

    @Column(name = "foodName")
    private String foodName;

    @Column(name = "foodPrice")
    private Integer foodPrice;

    @Column(name = "food_description")
    private String foodDescription;

    public RestaurantFood() {
    }

    public RestaurantFood(String restaurantName, String foodName, Integer foodPrice, String foodDescription) {
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescription = foodDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
