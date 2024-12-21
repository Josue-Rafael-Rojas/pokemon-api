package com.tecnica.pokeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FetchException extends RuntimeException{
    public FetchException(String message) {
        super(message);
    }
}
