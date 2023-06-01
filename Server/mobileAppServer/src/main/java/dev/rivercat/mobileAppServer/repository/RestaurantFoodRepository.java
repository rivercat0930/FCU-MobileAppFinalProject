package dev.rivercat.mobileAppServer.repository;

import dev.rivercat.mobileAppServer.model.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Long> {
    ArrayList<RestaurantFood> findFoodByRestaurantName(String restaurantName);

    @Modifying
    @Transactional
    void deleteFoodByRestaurantNameAndFoodName(@Param("restaurant_name")String restaurant, @Param("food_name") String foodNme);
}
