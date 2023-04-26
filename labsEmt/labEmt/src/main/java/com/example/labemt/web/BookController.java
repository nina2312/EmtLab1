package com.example.labemt.web;

import com.example.labemt.model.Author;
import com.example.labemt.model.Book;
import com.example.labemt.model.enumerations.Category;
import com.example.labemt.service.AuthorService;
import com.example.labemt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {


    private BookService bookService;

    private AuthorService authorService;
    /*@GetMapping
    public String getBookPage(Model model){
        List<Book> books = this.bookService.findAll();
        model.addAttribute("book", books);
        return "books.html";
    }*/
    public BookController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("book", books);
        model.addAttribute("bodyContent", "products");
        return "books.html";
    }


    @GetMapping("/add-new")
    public String addNewBook(Model model) {
        //List<Category> category = List.of(Category.values());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("category", Category.values());
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        //Object o = null;
        // Book book = this.bookService.findById(id);
        if (id != null) {
            Optional<Book> book = this.bookService.findById(id);
            List<Author> authors = this.authorService.findAll();
            model.addAttribute("author", authors);
            model.addAttribute("book", book);
            model.addAttribute("category", Category.values());
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }


    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam Long authorId,
            @RequestParam Integer availableCopies) {
        if (id != null) {
            this.bookService.edit(id, name, category, authorId, availableCopies);
        } else {
            //  Book book=new Book(id,name,category,authorId,kopii);
            this.bookService.save(name, category, authorId, availableCopies);
        }
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:books";
    }


}