package com.example.labemt.model.exceptions;

import com.example.labemt.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorFromCountryNotFound extends RuntimeException{
    public AuthorFromCountryNotFound(Country country) {
        super(String.format("Author from country: %s was not found!", country.toString()));
    }
}
