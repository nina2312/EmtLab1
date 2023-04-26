package com.example.labemt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super(String.format("Book with id: %ld was not found!",id));
    }
}
