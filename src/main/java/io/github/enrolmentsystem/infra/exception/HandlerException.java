package io.github.enrolmentsystem.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400(MethodArgumentNotValidException ex){
        var err = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(err.stream()
                .map(ErrorValidationData::new)
                .toList());
    }
    private record ErrorValidationData(String field, String msg) {
        public ErrorValidationData(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity userCasesExceptionHandler(ValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
