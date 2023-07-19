package com.test.demo.service;

import com.test.demo.dto.ActiveLimitOfferDTO;
import com.test.demo.dto.LimitOfferDTO;
import com.test.demo.dto.UpdateLimitOfferStatusDTO;
import com.test.demo.model.LimitOffer;

import java.util.List;

public interface LimitOfferService {
    public LimitOffer createLimitOffer(LimitOfferDTO limitOfferDTO);

    public void updateLimitOfferStatus(UpdateLimitOfferStatusDTO updateLimitOfferStatusDTO);

    public List<LimitOffer> getActiveLimitOffers(ActiveLimitOfferDTO activeLimitOffersDTO);
}
