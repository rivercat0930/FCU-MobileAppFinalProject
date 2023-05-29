package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.Restaurant;
import dev.rivercat.mobileAppServer.repository.RestaurantRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<Void> loginRestaurant(@RequestBody Restaurant restaurant) {
        boolean isRegister = restaurantRepository.findRestaurantByAccount(restaurant.getAccount()) == null;

        if (isRegister)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Restaurant targetRestaurant = restaurantRepository.findRestaurantByAccount(restaurant.getAccount());
        restaurant.setPassword(hash(restaurant.getAccount() + restaurant.getPassword()));

        boolean isMatch = restaurant.getAccount().equals(targetRestaurant.getAccount()) &&
                restaurant.getPassword().equals(targetRestaurant.getPassword());

        if (isMatch)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerRestaurant(@RequestBody Restaurant restaurant) {
        // check if lack any information

        // check if already have
        String account = restaurant.getAccount();
        boolean isInDB = restaurantRepository.findRestaurantByAccount(account) == null;

        if (!isInDB)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Restaurant postData = new Restaurant();
        postData.setRestaurantName(restaurant.getRestaurantName());
        postData.setAccount(restaurant.getAccount());
        postData.setPassword(hash(restaurant.getAccount() + restaurant.getPassword()));
        restaurantRepository.save(postData);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private String hash(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
