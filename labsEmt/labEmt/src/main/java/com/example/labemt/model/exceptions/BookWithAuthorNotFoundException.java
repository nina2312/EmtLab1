package com.example.labemt.model.exceptions;

import com.example.labemt.model.Author;
import com.example.labemt.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookWithAuthorNotFoundException extends RuntimeException{
    public BookWithAuthorNotFoundException(Author author) {
        super(String.format("Book with author: %s was not found!", author.getName()));
    }
}
