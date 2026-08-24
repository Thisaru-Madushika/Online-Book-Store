package com.example.book_service.controller;

import com.example.book_service.entity.Book;
import com.example.book_service.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller to handle HTTP requests
@RestController
// Sets the base URL path for all endpoints in this controller
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // Constructor-based dependency injection for BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint to add a new book to the database
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // Endpoint to retrieve a list of all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint to get a specific book by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update an existing book's details
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    // Endpoint to delete a book from the database by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}