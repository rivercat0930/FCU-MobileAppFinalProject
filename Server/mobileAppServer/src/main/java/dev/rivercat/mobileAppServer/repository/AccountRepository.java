package dev.rivercat.mobileAppServer.repository;

import dev.rivercat.mobileAppServer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
}
