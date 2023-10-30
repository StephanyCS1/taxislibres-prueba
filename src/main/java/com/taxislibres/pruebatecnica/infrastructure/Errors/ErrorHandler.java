package com.taxislibres.pruebatecnica.infrastructure.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja las excepciones y errores comunes en una aplicación Spring Boot.
 */
@RestControllerAdvice
public class ErrorHandler {
    /**
     * Maneja las excepciones de validación que ocurren cuando se utilizan anotaciones de validación en las solicitudes.
     *
     * @param ex La excepción de validación.
     * @return Un mapa de errores que contiene los campos y mensajes de error de la validación.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * Maneja excepciones de tiempo de ejecución (Runtime Exceptions) en la aplicación.
     *
     * @param exception La excepción de tiempo de ejecución.
     * @return Una respuesta de error con un mensaje personalizado.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> runtimeExeption(RuntimeException exception) {
        MessageResponse messageResponse = MessageResponse.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
}
