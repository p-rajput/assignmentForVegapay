package com.test.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.dao.AccountDao;
import com.test.demo.dto.AccountDTO;
import com.test.demo.model.Account;
import com.test.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;


    @Override
    public Optional<Account> getAccountDetails(Long accountId) {
        return accountDao.findById(accountId);
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.convertValue(accountDTO, Account.class);
        return accountDao.save(account);
    }
}
