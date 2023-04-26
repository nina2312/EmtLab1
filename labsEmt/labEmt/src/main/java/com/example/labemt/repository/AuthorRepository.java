package com.example.labemt.repository;

import com.example.labemt.model.Author;
import com.example.labemt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository  extends JpaRepository<Author,Long> {
    Optional<Author> findByCountryContaining(Country country);
    Optional<Author> findByName(String name);
}
