package br.ufc.crateus.npds.events.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDateException extends Exception {

    public InvalidDateException(){
        super("Data inválida");
    }
}
