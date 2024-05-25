package com.buymore.transaction.api.core.exceptions;

import com.buymore.transaction.api.core.utils.DateBuyMoreUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class BuymoreTransactionExceptionHandler implements ExceptionMapper<Exception> {

    private Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

    @Override
    public Response toResponse(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(DateBuyMoreUtils.convertLocalDateTimeToString(LocalDateTime.now()));
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setError("X99");
        if(exception instanceof  BuymoreTransactionApiException){
            if(((BuymoreTransactionApiException) exception).getStatus() != null){
                this.status = ((BuymoreTransactionApiException) exception).getStatus();
                errorMessage.setMessage(exception.getMessage());
                errorMessage.setError("X05");
            }
        }
        return Response.status(status.getStatusCode()).entity(errorMessage).build();
    }
}
