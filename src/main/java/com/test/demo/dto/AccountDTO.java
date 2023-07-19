package com.test.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = -461474054552440L;

    private Long accountId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Positive(message = "Account limit should be greater than 0")
    private Double accountLimit;

    @Positive(message = "Per transaction limit should be greater than 0")
    private Double perTransactionLimit;

    private Double lastAccountLimit;

    private Double lastPerTransactionLimit;

    private Date accountLimitUpdateTime;

    private Date perTransactionLimitUpdateTime;
}
