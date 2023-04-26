package com.example.labemt.service;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

   Optional<Author> findById(Long id);

    List<Author> findAll();

    Author findByCountry(Country country);

    Optional<Author> findByName(String name);

    Author create(String name, String surname, Long countryId);

    Optional<Author> create(Author author);

   void deleteById(Long id);

    Optional<Author> edit(Long id, Author author);

}
