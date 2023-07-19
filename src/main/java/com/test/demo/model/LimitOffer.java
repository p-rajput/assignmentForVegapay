package com.test.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "limit_offers")
public class LimitOffer implements Serializable {

    private static final long serialVersionUID = 46132054552440L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "offer_limit")
    private Double offerLimit;

    @Column(name = "status")
    private String status = "PENDING";

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "limit_type")
    private String limitType;

    @Column(name = "offer_expiry_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerExpiryTime;

    @Column(name = "offer_activation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerActivationTime;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Account account;

    @PrePersist
    private void prePersist() {
        createdAt = new Date();
    }

}
