package dev.rivercat.mobileAppServer.repository;

import dev.rivercat.mobileAppServer.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface HistoryRepository extends JpaRepository<History, Long> {
    ArrayList<History> findHistoryByUsername(String username);
}
