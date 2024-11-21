package com.example.librarysystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Lägger till en ny bok
    public Book addBook(Book book) {
        
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooksByGenre(String genreName) {
        return bookRepository.findByGenreName(genreName);
    }

    // Uppdatera en existirande bok
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    // Ta bort en bok
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        if (!book.getAvailable()) {
            throw new RuntimeException("Cannot delete a checked-out book.");
        }
        bookRepository.delete(book);
    }

    // hämtar en bok från id
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found."));
    }

    // söker en bok
    public List<Book> searchBooks(String title, String authorName) {
        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (authorName != null) {
            return bookRepository.findByAuthorFullNameContainingIgnoreCase(authorName);
        } else {
            return bookRepository.findAll();
        }
    }

}
