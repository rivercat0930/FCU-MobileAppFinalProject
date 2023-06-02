package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.History;
import dev.rivercat.mobileAppServer.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurant_c2")
public class RestaurantCurrentShopController {
    @Autowired
    protected HistoryRepository historyRepository;

    @PostMapping("/done")
    public ResponseEntity<Void> doneMeals(@RequestBody History history) {
        ArrayList<History> histories = (ArrayList<History>) historyRepository.findAll();
        History targetHistory = null;

        for (History currentHistory : histories) {
            boolean isThisData = !currentHistory.isDone() &&
                    currentHistory.getShopName().equals(history.getShopName()) &&
                    currentHistory.getUsername().equals(history.getUsername()) &&
                    currentHistory.getHistory().equals(history.getHistory());

            if (isThisData) {
                targetHistory = currentHistory;
                break;
            }
        }

        if (targetHistory != null) {
            targetHistory.setDone(true);
            historyRepository.save(targetHistory);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{restaurant_name}/get_order")
    public ResponseEntity<ArrayList<History>> getOrders(@PathVariable("restaurant_name") String restaurant) {
        ArrayList<History> histories = (ArrayList<History>) historyRepository.findAll();

        for (int i = 0; i < histories.size(); i++) {
            History currentHistory = histories.get(i);

            if (currentHistory.isDone()) {
                histories.remove(currentHistory);
                i = i - 1;
            }
            else if (!currentHistory.getShopName().equals(restaurant))
                histories.remove(currentHistory);
        }

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
