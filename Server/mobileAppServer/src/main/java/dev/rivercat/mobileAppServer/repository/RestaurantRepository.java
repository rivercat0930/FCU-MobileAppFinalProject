package dev.rivercat.mobileAppServer.repository;

import dev.rivercat.mobileAppServer.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findRestaurantByAccount(String account);
}
