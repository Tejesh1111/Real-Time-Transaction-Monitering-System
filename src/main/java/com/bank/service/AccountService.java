package com.bank.service;

import com.bank.entity.Account;
import com.bank.entity.Transaction;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientBalanceException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private static final double DEFAULT_BALANCE = 100000.0;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(String name) {
        Account account = new Account();
        account.setName(name);
        account.setBalance(DEFAULT_BALANCE);
        Account saved = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(saved.getId());
        transaction.setType("DEPOSIT");
        transaction.setAmount(DEFAULT_BALANCE);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return saved;
    }

    public Double getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getBalance();
    }

    public Account deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        Account updated = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return updated;
    }

    public Account withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account updated = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);

        return updated;
    }

    @Transactional
    public void transfer(TransferRequest request) {
        Account sender = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));
        Account receiver = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (sender.getBalance() < request.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance in sender account");
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        accountRepository.save(sender);

        receiver.setBalance(receiver.getBalance() + request.getAmount());
        accountRepository.save(receiver);

        Transaction senderTx = new Transaction();
        senderTx.setAccountId(sender.getId());
        senderTx.setType("TRANSFER_SENT");
        senderTx.setAmount(request.getAmount());
        senderTx.setTimestamp(LocalDateTime.now());
        transactionRepository.save(senderTx);

        Transaction receiverTx = new Transaction();
        receiverTx.setAccountId(receiver.getId());
        receiverTx.setType("TRANSFER_RECEIVED");
        receiverTx.setAmount(request.getAmount());
        receiverTx.setTimestamp(LocalDateTime.now());
        transactionRepository.save(receiverTx);
    }
}
