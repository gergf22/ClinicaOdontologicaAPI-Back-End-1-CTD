package com.BackEnd1.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> badRequest (BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mensaje: " + bre.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> notFound (NotFoundException nfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje: " + nfe.getMessage());
    }
}
