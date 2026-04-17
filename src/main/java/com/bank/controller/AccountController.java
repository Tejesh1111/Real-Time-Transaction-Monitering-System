package com.bank.controller;

import com.bank.entity.Account;
import com.bank.request.TransferRequest;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String name) {
        Account account = accountService.createAccount(name);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
        Double balance = accountService.getBalance(id);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id,
                                           @RequestParam Double amount) {
        Account account = accountService.deposit(id, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id,
                                            @RequestParam Double amount) {
        Account account = accountService.withdraw(id, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest request) {
        accountService.transfer(request);
        return ResponseEntity.ok("Transfer successful");
    }
}
