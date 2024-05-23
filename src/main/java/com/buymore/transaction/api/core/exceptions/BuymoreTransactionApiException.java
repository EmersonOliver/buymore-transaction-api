package com.buymore.transaction.api.core.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class BuymoreTransactionApiException extends RuntimeException {

    private Response.Status status;

    public BuymoreTransactionApiException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BuymoreTransactionApiException(String msg, Response.Status status) {
        super(msg);
        this.status = status;
    }

    public BuymoreTransactionApiException(String message) {
        super(message);
    }
}
