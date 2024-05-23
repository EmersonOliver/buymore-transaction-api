package com.buymore.transaction.api.core.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private String message;
    private String timestamp;
    private String error;

    public ErrorMessage(String message, String timestamp, String error) {
        this.message = message;
        this.timestamp = timestamp;
        this.error = error;
    }

    public ErrorMessage() {
    }
}
