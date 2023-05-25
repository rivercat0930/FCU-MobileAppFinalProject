package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.Restaurant;
import dev.rivercat.mobileAppServer.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Restaurant>> getAllRestaurant() {
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantRepository.findAll();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
