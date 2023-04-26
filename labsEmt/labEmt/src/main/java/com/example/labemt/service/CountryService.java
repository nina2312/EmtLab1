package com.example.labemt.service;

import com.example.labemt.model.Country;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();


    Optional<Country> findById(Long id);

    Country save(String name, String continent);

    Country create(String name, String continent);

    Optional<Country> create(Country country);

    Optional<Country> edit(Long id,Country country);

    void deleteById(Long id);
}
