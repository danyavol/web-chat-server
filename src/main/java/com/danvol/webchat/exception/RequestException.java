package com.danvol.webchat.exception;

import lombok.Data;


@Data
public class RequestException {
    private String error;

    public RequestException(String message) {
        this.error = message;
    }

}
