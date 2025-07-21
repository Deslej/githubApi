package com.codibly.githubapi.exception;

import com.codibly.githubapi.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotExistingUserException.class)
    public ResponseEntity<ErrorResponseDto> handleNotExistingUserException(NotExistingUserException exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
