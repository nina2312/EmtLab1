package com.example.labemt.service.impl;

import com.example.labemt.model.Author;
import com.example.labemt.model.Country;
import com.example.labemt.model.exceptions.AuthorFromCountryNotFound;
import com.example.labemt.model.exceptions.AuthorNotFoundException;
import com.example.labemt.model.exceptions.CountryNotFoundException;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findByCountry(Country country) {
        this.countryRepository.findById(country.getId());
        return this.authorRepository.findByCountryContaining(country).orElseThrow(()-> new AuthorFromCountryNotFound(country));
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country=this.countryRepository.findById(countryId).orElseThrow(()->new CountryNotFoundException(countryId));
        Author author=new Author(name, surname,country);
        return this.authorRepository.save(author);
    }

    @Override
    public Optional<Author> create(Author author) {
        Author author1=new Author(author.getName(), author.getSurname(),author.getCountry());
        this.authorRepository.save(author1);
        return Optional.of(author1);
    }

    @Override
    public void deleteById(Long id) {
         this.authorRepository.deleteById(id);
    }

    @Override
    public Optional<Author> edit(Long id, Author author) {
        Author author1=this.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
        author1.setName(author.getName());
        author1.setSurname(author.getSurname());
        author1.setCountry(author.getCountry());

        return Optional.of(this.authorRepository.save(author1));
    }
}
