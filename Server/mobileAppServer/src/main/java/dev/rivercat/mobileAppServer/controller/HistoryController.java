package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.History;
import dev.rivercat.mobileAppServer.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    protected HistoryRepository historyRepository;

    @GetMapping("/{user}")
    public ResponseEntity<ArrayList<String>> getHistories(@PathVariable("user") String user) {
        ArrayList<History> userHistory = historyRepository.findHistoryByUsername(user);

        ArrayList<String> retString = new ArrayList<>();
        // return last 5 history
        for (int i = 0; i < 5; i++) {
            // size check
            if (userHistory.size() - 1 - i < 0)
                break;

            History currentHistory = userHistory.get(userHistory.size() - 1 - i);
            retString.addAll(currentHistory.getHistory());
        }

        return new ResponseEntity<>(retString, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> getHistoryFromClient(@RequestBody History history) {
        historyRepository.save(history);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
