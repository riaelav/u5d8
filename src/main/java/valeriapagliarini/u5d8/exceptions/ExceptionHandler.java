package valeriapagliarini.u5d8.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import valeriapagliarini.u5d8.payloads.ErrorsDTO;

import java.time.LocalDateTime;

@RestControllerAdvice

public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsDTO handleBadRequest(BadRequestException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    // eccezioni non gestite dagli altri metodi server error
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorsDTO handleServerError(Exception ex) {
        ex.printStackTrace(); // E' importante avere il print dello stack trace
        return new ErrorsDTO("C'è stato un errore generico! ", LocalDateTime.now());

    }
}
