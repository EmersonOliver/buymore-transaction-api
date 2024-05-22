package com.buymore.transaction.api.adapters.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private String clientId;
    private String clientName;
    private BigDecimal wallet;
    private String typeClient;
    private String document;
    private String email;
}
