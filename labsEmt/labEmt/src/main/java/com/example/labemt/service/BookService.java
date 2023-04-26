package com.example.labemt.service;

import com.example.labemt.model.Book;
import com.example.labemt.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book findByAuthor(Long authorId);
    Optional<Book> findByName(String name);
    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id, Book book);
    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save(Book book);
    //Book create(String name, Category category, Long authorId, Integer availableCopies);
    void deleteById(Long id);
    Optional<Book> rent(Long id);
    Page<Book> listBooksPaginated(Pageable pageable);

    Optional<Book> MaskAsTaken (Long id);
}
