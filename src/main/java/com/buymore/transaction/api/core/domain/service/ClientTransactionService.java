package com.buymore.transaction.api.core.domain.service;

import com.buymore.transaction.api.adapters.client.ClientApiRestClient;
import com.buymore.transaction.api.adapters.client.ClientResponse;
import com.buymore.transaction.api.adapters.producer.KafkaTransactionProducer;
import com.buymore.transaction.api.core.domain.dto.TransactionDTO;
import com.buymore.transaction.api.core.domain.entity.ClientTransactionEntity;
import com.buymore.transaction.api.core.domain.records.TransactionRecord;
import com.buymore.transaction.api.core.domain.repository.ClientTransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ApplicationScoped
public class ClientTransactionService {


    @Inject
    @RestClient
    ClientApiRestClient clientApiRestClient;

    @Inject
    ClientTransactionRepository transactionRepository;

    @Inject
    KafkaTransactionProducer kafkaSender;

    @Transactional
    public void sendTransaction(TransactionRecord req) {
        ClientResponse clientSender = this.clientApiRestClient.loadClient(req.clientIdSend());
        ClientResponse clientReceiver = this.clientApiRestClient.loadClient(req.clientIdReceive());

        if (clientSender != null && clientReceiver != null) {
            ClientTransactionEntity trEntity = new ClientTransactionEntity();
            trEntity.setClientIdReceive(clientReceiver.getClientId());
            trEntity.setClientIdSend(clientSender.getClientId());

            if (req.transactionValue().doubleValue() <= clientSender.getWallet().doubleValue()) {
                trEntity.setMovementDate(new Date());
                trEntity.setTransactionValue(req.transactionValue());
                this.transactionRepository.persistAndFlush(trEntity);

                kafkaSender.senderTransaction(TransactionDTO.builder()
                        .transactionId(trEntity.getTransactionId().toString())
                        .clientIdReceive(trEntity.getClientIdReceive())
                        .typeTransaction(trEntity.getTypeTransaction())
                        .movementDate(this.convertDateToString(trEntity.getMovementDate()))
                        .transactionValue(trEntity.getTransactionValue())
                        .build());
            }
        }
    }

    private String convertDateToString(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.toString();
    }

}
