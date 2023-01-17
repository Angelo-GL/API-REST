package com.company.desafio.desafioapi.controller.exceptions;

import com.company.desafio.desafioapi.exception.ObjectNotFoundExceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandle {
    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandarError> objectNotFound (ObjectNotFoundExceptions e, HttpServletRequest requeste){
        StandarError error = new StandarError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
