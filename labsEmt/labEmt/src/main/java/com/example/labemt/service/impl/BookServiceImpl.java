package com.example.labemt.service.impl;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.model.exceptions.AuthorNotFoundException;
import com.example.labemt.model.exceptions.BookNotFoundException;
import com.example.labemt.model.exceptions.BookWithAuthorNotFoundException;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.BookRepository;
import com.example.labemt.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book>  findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Book findByAuthor(Long authorId) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        return this.bookRepository.findByAuthorContaining(author).orElseThrow(()-> new BookWithAuthorNotFoundException(author));
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies)  {
        Book book1=this.bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book1.setName(name);
        Author author= this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        book1.setAuthor(author);
        book1.setCategory(category);
        book1.setAvailableCopies(availableCopies);
        this.bookRepository.save(book1);
        return Optional.of(book1);
    }

    @Override
    public Optional<Book> edit(Long id, Book book) {
        Book book1 = this.findById(id).orElseThrow(()->new BookNotFoundException(id));

        book1.setName(book.getName());
        book1.setCategory(book.getCategory());
        book1.setAuthor(book.getAuthor());
        book1.setAvailableCopies(book.getAvailableCopies());
        this.bookRepository.save(book1);
        return Optional.of(book1);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author=this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));

        Book book = new Book(name,category,author,availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(Book book) {
        Book book1 = new Book(book.getName(),book.getCategory(),book.getAuthor(),book.getAvailableCopies());
        this.bookRepository.save(book1);
        return Optional.of(book1);
    }

   /* @Override
    public Book create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author=this.authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException(authorId));
        Book book= new Book(name,category,author,availableCopies);
        return this.bookRepository.save(book);
    }*/

    @Override
    public void deleteById(Long id) {

        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book b=this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        if(b.getAvailableCopies()>0){
            b.setAvailableCopies(b.getAvailableCopies()-1);
            this.bookRepository.save(b);
        }
        return Optional.of(b);
    }
    @Override
    public Page<Book> listBooksPaginated(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> MaskAsTaken(Long id) {
        Book book= this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        int count = book.getAvailableCopies()-1;
        book.setAvailableCopies(count);
        this.bookRepository.save(book);
        return Optional.of(book);

    }


}
