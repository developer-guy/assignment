package com.todo.todoapp.exception;

public class DuplicateResourceFound extends BaseException {
    public DuplicateResourceFound(int code, String message) {
        super(code, message);
    }
}
