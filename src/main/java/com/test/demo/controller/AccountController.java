package com.test.demo.controller;

import com.test.demo.dto.AccountDTO;
import com.test.demo.dto.ActiveLimitOfferDTO;
import com.test.demo.dto.LimitOfferDTO;
import com.test.demo.dto.UpdateLimitOfferStatusDTO;
import com.test.demo.model.Account;
import com.test.demo.model.LimitOffer;
import com.test.demo.service.AccountService;
import com.test.demo.service.LimitOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    LimitOfferService limitOfferService;

    @Autowired
    AccountService accountService;

    @PostMapping("/limit-offer")
    public LimitOffer createLimitOffer(@RequestBody LimitOfferDTO limitOfferDTO) {
        return limitOfferService.createLimitOffer(limitOfferDTO);
    }

    @GetMapping("/limit-offer")
    public List<LimitOffer> getActiveLimitOffers(@RequestParam("account_id") Long accountId, @RequestParam("active_date") LocalDate activeDate) {
        ActiveLimitOfferDTO activeLimitOfferDTO = new ActiveLimitOfferDTO();
        activeLimitOfferDTO.setActiveDate(activeDate);
        activeLimitOfferDTO.setAccountId(accountId);
        return limitOfferService.getActiveLimitOffers(activeLimitOfferDTO);
    }

    @PutMapping("/limit-offer/{limitOfferId}")
    public String getActiveLimitOffers(@PathVariable Long limitOfferId, @RequestParam("status") String status) {
        UpdateLimitOfferStatusDTO updateLimitOfferStatusDTO = new UpdateLimitOfferStatusDTO();
        updateLimitOfferStatusDTO.setLimitOfferId(limitOfferId);
        updateLimitOfferStatusDTO.setStatus(status);
        limitOfferService.updateLimitOfferStatus(updateLimitOfferStatusDTO);
        return "Success";
    }

    @PostMapping("/create-account")
    public Account createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable("accountId") Long accountId) {
        Optional<Account> account = accountService.getAccountDetails(accountId);
        if (account.isPresent()) {
            return account.get();
        }
        throw new RuntimeException("AccountId is not valid");
    }
}
