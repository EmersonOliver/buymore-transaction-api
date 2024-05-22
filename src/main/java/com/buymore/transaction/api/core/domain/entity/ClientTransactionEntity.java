package com.buymore.transaction.api.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_transaction", schema = "buymore_transactional")
public class ClientTransactionEntity {

    @Id
    @Column(name = "transaction_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;

    @Column(name = "client_id_send", nullable = false)
    private String clientIdSend;

    @Column(name = "client_id_receive", nullable = false)
    private String clientIdReceive;

    @Column(name = "type_transaction")
    private String typeTransaction;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "movement_date", nullable = false)
    private Date movementDate;

    @Column(name = "transaction_value", nullable = false)
    private BigDecimal transactionValue;

}
