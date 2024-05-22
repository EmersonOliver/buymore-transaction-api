package com.buymore.transaction.api.core.domain.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionDTO {

    private String transactionId;
    private String clientIdSend;
    private String clientIdReceive;
    private String typeTransaction;
    private String movementDate;
    private BigDecimal transactionValue;

}
