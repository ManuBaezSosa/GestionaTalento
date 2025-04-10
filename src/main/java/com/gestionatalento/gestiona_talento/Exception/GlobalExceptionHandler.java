package com.gestionatalento.gestiona_talento.Exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gestionatalento.gestiona_talento.Response.GenericResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCodigoMensaje("400");
        genericResponse.setMensaje("Existen campos con valores incorrectos");

        Map<String, String> errors = new HashMap<>();
        BindingResult result = ex.getBindingResult();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        genericResponse.setObjeto(errors);
        return genericResponse;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public GenericResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCodigoMensaje("400");
        genericResponse.setMensaje("El parámetro recibido no cumple con el tipo de dato requerido: '" + ex.getValue() + "'");
        genericResponse.setObjeto(null);
        return genericResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public GenericResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setCodigoMensaje("400");

        Throwable causa = ex.getCause();
        String mensaje = "El objeto recibido no cumple con el tipo de dato requerido en los campos.";
        if (causa instanceof InvalidFormatException invalidFormatException) {
            if (!invalidFormatException.getPath().isEmpty()) {
                String campo = invalidFormatException.getPath().get(0).getFieldName();
                String valor = String.valueOf(invalidFormatException.getValue());
                mensaje = String.format("El campo '%s' requiere un tipo de dato diferente. Valor recibido: '%s'", campo, valor);
            }
        }

        genericResponse.setMensaje(mensaje);
        genericResponse.setObjeto(null);
        return genericResponse;
    }
}
