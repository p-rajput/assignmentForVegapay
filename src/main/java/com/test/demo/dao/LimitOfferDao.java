package com.test.demo.dao;

import com.test.demo.model.LimitOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitOfferDao extends JpaRepository<LimitOffer, Long> {

    @Query("SELECT lo FROM LimitOffer lo " +
            "WHERE lo.accountId = :accountId " +
            "AND lo.status = 'PENDING'")
    List<LimitOffer> findActiveOffers(@Param("accountId") Long accountId);// assuming that we have Nonclustered index on combination of accountId and status
}

