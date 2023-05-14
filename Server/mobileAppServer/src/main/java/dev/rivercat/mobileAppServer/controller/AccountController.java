package dev.rivercat.mobileAppServer.controller;

import dev.rivercat.mobileAppServer.model.Account;
import dev.rivercat.mobileAppServer.repository.AccountRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    protected AccountRepository accountRepository;

    /**
     * This function is register a new account,
     * it will check this new account is already create,
     * if create then do nothing,
     * or it will create and save into database.
     *
     * @param account
     * send account type and contain all account information
     *
     * @return
     * 204 is no content or miss some argument,
     * 403 is already register,
     * 201 is successful.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        boolean isLackInformation = account.getUsername() == null ||
                account.getPassword() == null ||
                account.getEmail() == null;

        boolean isRegister = accountRepository.findAccountByUsername(account.getUsername()) == null &&
                accountRepository.findAccountByEmail(account.getEmail()) == null;

        if (isLackInformation)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        if (isRegister) {
            account.setPassword(hash(account.getUsername() + account.getPassword()));
            accountRepository.save(account);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * This will let the user login,
     * if no account will return NOT_FOUND,
     * then check the information about the account database.
     *
     * @param account
     * account can ignore the email information.
     *
     * @return
     * 404 is not found the account information in database,
     * 200 is login successfully,
     * 403 is login failed.
     */
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        boolean isRegister = accountRepository.findAccountByUsername(account.getUsername()) == null;

        if (isRegister)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Account targetAccount = accountRepository.findAccountByUsername(account.getUsername());
        account.setPassword(hash(account.getUsername() + account.getPassword()));

        boolean isMatch = account.getUsername().equals(targetAccount.getUsername()) &&
                account.getPassword().equals(targetAccount.getPassword());

        if (isMatch)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * hash the string
     *
     * @param str
     * the string want to hash
     *
     * @return
     * hash string
     */
    private String hash(String str) {
        return DigestUtils.sha256Hex(str);
    }
}
