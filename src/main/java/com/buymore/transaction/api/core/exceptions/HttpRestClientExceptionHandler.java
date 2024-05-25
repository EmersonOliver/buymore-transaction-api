package com.buymore.transaction.api.core.exceptions;

import com.buymore.transaction.api.adapters.client.ClientResponse;
import com.buymore.transaction.api.core.domain.entity.ClientTransactionEntity;
import com.buymore.transaction.api.core.utils.DateBuyMoreUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.time.LocalDateTime;
import java.util.Date;

@Provider
public class HttpRestClientExceptionHandler implements ResponseExceptionMapper<Exception> {

    @Override
    public Exception toThrowable(Response response) {
            if(response.getStatus() == 403) {
                throw new BuymoreTransactionApiException("Transaction deny", Response.Status.UNAUTHORIZED);
            }
        return null;
    }
}
