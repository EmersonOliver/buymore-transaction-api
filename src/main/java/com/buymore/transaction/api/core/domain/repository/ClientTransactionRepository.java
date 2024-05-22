package com.buymore.transaction.api.core.domain.repository;

import com.buymore.transaction.api.core.domain.entity.ClientTransactionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientTransactionRepository implements PanacheRepository<ClientTransactionEntity> {


}
