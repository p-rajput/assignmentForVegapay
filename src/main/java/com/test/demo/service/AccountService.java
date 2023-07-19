package com.test.demo.service;

import com.test.demo.dto.AccountDTO;
import com.test.demo.model.Account;

import java.util.Optional;

public interface AccountService {
    public Optional<Account> getAccountDetails(Long accountId);

    public Account createAccount(AccountDTO accountDTO);
}
