package com.knubisoft.bmwtesttask.exception;

import com.knubisoft.bmwtesttask.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongStatusCodeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleWrongStatusCodeException(WrongStatusCodeException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(ResponseWithoutBodyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleResponseWithoutBodyException() {
        return new ErrorResponse("There is no data in the response received, something went wrong...");
    }

    @ExceptionHandler(WrongLengthOfResponseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse handleWrongStatusCodeException() {
        return new ErrorResponse("The number of users received in the response does not correspond to reality, something went wrong...");
    }

}
