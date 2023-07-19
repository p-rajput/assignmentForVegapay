package com.test.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitOfferDTO implements Serializable {

    private static final long serialVersionUID = -461474054554240L;

    public enum LimitTypeEnum {
        ACCOUNT_LIMIT,PER_TRANSACTION_LIMIT
    }

    @NotNull(message = "Account ID is required")
    private Long accountId;

    private LimitTypeEnum limitType = LimitTypeEnum.PER_TRANSACTION_LIMIT;

    @Positive(message = "New Limit should be greater than 0")
    private Double newLimit;

    private LocalDateTime offerActivationTime;

    @NotNull(message = "Offer Expiry Time is required")
    @Future(message = "Offer Expiry Time should be in the future")
    private LocalDateTime offerExpiryTime;
}

