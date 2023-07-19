package com.test.demo.service.impl;

import com.test.demo.dao.AccountDao;
import com.test.demo.dao.LimitOfferDao;
import com.test.demo.dto.ActiveLimitOfferDTO;
import com.test.demo.dto.LimitOfferDTO;
import com.test.demo.dto.UpdateLimitOfferStatusDTO;
import com.test.demo.model.Account;
import com.test.demo.model.LimitOffer;
import com.test.demo.service.LimitOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LimitOfferServiceImpl implements LimitOfferService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    LimitOfferDao limitOfferDao;

    public LimitOffer createLimitOffer(LimitOfferDTO limitOfferDTO) {
        Optional<Account> optionalAccount = accountDao.findById(limitOfferDTO.getAccountId());
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (limitOfferDTO.getNewLimit() > account.getAccountLimit() && limitOfferDTO.getOfferExpiryTime().isAfter(limitOfferDTO.getOfferActivationTime())) {
                LimitOffer limitOffer = new LimitOffer();
                limitOffer.setAccountId(limitOfferDTO.getAccountId());
                limitOffer.setOfferLimit(limitOfferDTO.getNewLimit());
                limitOffer.setOfferActivationTime(Date.from(limitOfferDTO.getOfferActivationTime().toInstant(ZoneOffset.UTC)));
                limitOffer.setOfferExpiryTime(Date.from(limitOfferDTO.getOfferExpiryTime().toInstant(ZoneOffset.UTC)));
                limitOffer.setLimitType(limitOfferDTO.getLimitType().name());
                return limitOfferDao.save(limitOffer);
            }
        }
        throw new RuntimeException("AccountId is not valid");
    }

    public List<LimitOffer> getActiveLimitOffers(ActiveLimitOfferDTO activeLimitOffersDTO) {
        List<LimitOffer> limitOffers = limitOfferDao.findActiveOffers(activeLimitOffersDTO.getAccountId());// assuming active offerLimit list for a accountId is not too big to bring in memory(RAM)
        return limitOffers.parallelStream()
                .filter(limitOffer -> limitOffer.getOfferActivationTime().toInstant().atZone(ZoneOffset.UTC).toLocalDate().isBefore(activeLimitOffersDTO.getActiveDate()))
                .filter(limitOffer -> limitOffer.getOfferExpiryTime().toInstant().atZone(ZoneOffset.UTC).toLocalDate().isAfter(activeLimitOffersDTO.getActiveDate()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateLimitOfferStatus(UpdateLimitOfferStatusDTO updateLimitOfferStatusDTO) {

        try{
            Long limitOfferId = updateLimitOfferStatusDTO.getLimitOfferId();
            String status = updateLimitOfferStatusDTO.getStatus();

            Optional<LimitOffer> optionalLimitOffer = limitOfferDao.findById(limitOfferId);
            if (optionalLimitOffer.isPresent()) {
                LimitOffer limitOffer = optionalLimitOffer.get();
                limitOffer.setStatus(status);
                limitOfferDao.save(limitOffer);
                if ("ACCEPTED".equalsIgnoreCase(status)) {
                    Account account = limitOffer.getAccount();
                    Double newLimit = limitOffer.getOfferLimit();
                    if(limitOffer.getLimitType().equalsIgnoreCase(String.valueOf(LimitOfferDTO.LimitTypeEnum.ACCOUNT_LIMIT))){
                        account.setLastAccountLimit(account.getAccountLimit());
                        account.setAccountLimit(newLimit);
                        account.setAccountLimitUpdateTime(limitOffer.getCreatedAt());
                    }else{
                        account.setLastPerTransactionLimit(account.getPerTransactionLimit());
                        account.setPerTransactionLimit(newLimit);
                        account.setPerTransactionLimitUpdateTime(limitOffer.getCreatedAt());
                    }
                    accountDao.save(account);
                }
            }
            throw new RuntimeException("Limit Offer Id is not valid");
        }catch (Exception e){
            log.error("Error in updateLimitOfferStatus: ",e);
            throw new RuntimeException("Internal Server Error");
        }
    }
}
