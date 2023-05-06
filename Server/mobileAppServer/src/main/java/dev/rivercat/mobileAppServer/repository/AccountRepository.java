package dev.rivercat.mobileAppServer.repository;

import dev.rivercat.mobileAppServer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String email);
}
