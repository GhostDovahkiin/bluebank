package com.gama.projeto.bluebank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(){
        super("Transaction request not found or null");
    }
}
