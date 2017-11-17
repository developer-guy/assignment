package com.todo.todoapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String noResourceFoundException(ResourceNotFoundException resourceNotFoundException) {
        return convertError(resourceNotFoundException);
    }

    @ExceptionHandler(value = DuplicateResourceFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String duplicateElementFound(DuplicateResourceFound duplicateResourceFound) {
        return convertError(duplicateResourceFound);
    }

    private String convertError(ResourceError resourceError) {
        return String.format("Error code: %d Error message: %s", resourceError.getErrorCode(), resourceError.getErrorMessage());
    }

}
