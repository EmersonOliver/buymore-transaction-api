package com.buymore.transaction.api.adapters.resources;

import com.buymore.transaction.api.core.domain.records.TransactionRecord;
import com.buymore.transaction.api.core.domain.service.ClientTransactionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/transaction")
public class TransactionResource {

    @Inject
    ClientTransactionService clientTransactionService;

    @POST
    public Response sendTransaction(TransactionRecord request) {
        this.clientTransactionService.sendTransaction(request);
        return Response.ok().build();
    }


}
