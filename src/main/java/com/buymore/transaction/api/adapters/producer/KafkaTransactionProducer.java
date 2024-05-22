package com.buymore.transaction.api.adapters.producer;


import com.buymore.transaction.api.core.domain.dto.TransactionDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class KafkaTransactionProducer {

    @Inject
    Logger LOG;

    @Inject
    @Channel("buymore-transaction-balance")
    Emitter<TransactionDTO> transactionDTOEmitter;


    public void senderTransaction(TransactionDTO sender){
        LOG.warn("Sending message to kafka server...");
        this.transactionDTOEmitter.send(sender).toCompletableFuture().join();
    }
}
