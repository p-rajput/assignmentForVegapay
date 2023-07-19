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
@Table(name = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 46113054552440L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//account_id

    @Column(name = "customer_id")
    private Long userId;//customer_id

    @Column(name = "account_limit")
    private Double accountLimit;

    @Column(name = "per_transaction_limit")
    private Double perTransactionLimit;

    @Column(name = "last_account_limit")
    private Double lastAccountLimit;

    @Column(name = "last_per_transaction_limit")
    private Double lastPerTransactionLimit;

    @Column(name = "account_limit_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountLimitUpdateTime;

    @Column(name = "per_transaction_limit_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perTransactionLimitUpdateTime;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        createdAt = new Date();
        updatedAt = createdAt;
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}

