package com.example.librarysystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Lägger till en ny bok 
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.ok(createdBook);
    }

    // uppdaterar boken
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    // Tar bort boken
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully.";
    }

    // hämtar boken baserat på id
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Söker boker
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, author);
    }

    //Söker genra
    @GetMapping("/search-by-genre")
    public ResponseEntity<List<Book>> searchBooksByGenre(@RequestParam String genre) {
        List<Book> books = bookService.searchBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{bookId}/genres/{genreId}")
    public ResponseEntity<Book> addGenreToBook(@PathVariable Long bookId, @PathVariable Long genreId) {
        Book updatedBook = bookService.addGenreToBook(bookId, genreId);
        return ResponseEntity.ok(updatedBook);
    }
}
