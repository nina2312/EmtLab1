package com.example.labemt.model.exceptions;

public class PasswordNotMatchingException extends RuntimeException{
    public PasswordNotMatchingException() {
        super("Passwords do not match exception.");
    }
}
