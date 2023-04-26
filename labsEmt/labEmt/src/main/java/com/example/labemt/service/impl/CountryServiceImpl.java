package com.example.labemt.service.impl;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.Country;
import com.example.labemt.model.exceptions.AuthorNotFoundException;
import com.example.labemt.model.exceptions.CountryNotFoundException;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.service.AuthorService;
import com.example.labemt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Country save(String name, String continent) {
        Country country=new Country(name,continent);
        return this.countryRepository.save(country);

    }

    @Override
    public Country create(String name, String continent){
        Country country= new Country(name,continent);
        return this.countryRepository.save(country);
    }

    @Override
    public Optional<Country> create(Country country) {
        Country country1=new Country(country.getName(), country.getContinent());
        this.countryRepository.save(country1);
        return Optional.of(country1);
    }

    @Override
    public Optional<Country> edit(Long id, Country country) {
        Country country1=this.findById(id).orElseThrow(()->new CountryNotFoundException(id));
        country1.setName(country.getName());
        country1.setContinent(country.getContinent());
        this.countryRepository.save(country1);
        return Optional.of(country1);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

}
