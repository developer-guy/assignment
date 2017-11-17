package com.todo.todoapp.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(int code, String message) {
        super(code, message);
    }
}
