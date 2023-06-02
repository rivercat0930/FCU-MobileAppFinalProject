package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.RestaurantFood;
import dev.rivercat.mobileAppServer.repository.RestaurantFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurant_food")
public class RestaurantFoodController {
    @Autowired
    RestaurantFoodRepository restaurantFoodRepository;

    @GetMapping("/{restaurant_name}")
    public ResponseEntity<ArrayList<RestaurantFood>> getFoods(@PathVariable("restaurant_name") String name) {
        ArrayList<RestaurantFood> ret = restaurantFoodRepository.findFoodByRestaurantName(name);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> postFoods(@RequestBody RestaurantFood restaurantFood) {
        restaurantFoodRepository.save(restaurantFood);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteFoods(@RequestBody RestaurantFood restaurantFood) {
        restaurantFoodRepository.deleteFoodByRestaurantNameAndFoodName(restaurantFood.getRestaurantName(), restaurantFood.getFoodName());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
