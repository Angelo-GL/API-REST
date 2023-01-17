package com.company.desafio.desafioapi.exception;

public class ObjectNotFoundExceptions extends RuntimeException{
    public ObjectNotFoundExceptions(String message) {
        super(message);
    }

    public ObjectNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
