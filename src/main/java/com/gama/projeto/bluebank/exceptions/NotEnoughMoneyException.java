package com.gama.projeto.bluebank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(){
        super("The transaction amount is higher than your account amount");
    }
}
