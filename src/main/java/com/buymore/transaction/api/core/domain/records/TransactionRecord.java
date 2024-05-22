package com.buymore.transaction.api.core.domain.records;

import java.math.BigDecimal;

public record TransactionRecord(String clientIdSend, String clientIdReceive, String typeTransaction,
                                BigDecimal transactionValue) {
}
