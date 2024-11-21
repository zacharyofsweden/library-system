package com.example.librarysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Author;
import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Genre;
import com.example.librarysystem.repository.AuthorRepository;
import com.example.librarysystem.repository.BookRepository;
import com.example.librarysystem.repository.GenreRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    // Lägger till en ny bok
    public Book addBook(Book book) {
        
        Long authorId = book.getAuthor().getAuthorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found."));

        
        book.setAuthor(author);

        book.setAvailable(true);

        return bookRepository.save(book);
    }

    // Hämta all böker
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Söker en bok baserad på genra
    public List<Book> searchBooksByGenre(String genreName) {
        return bookRepository.findByGenreName(genreName);
    }

    // Updatera en exisisterade en bok
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    // Tarbort en bok
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        if (!book.getAvailable()) {
            throw new RuntimeException("Cannot delete a checked-out book.");
        }
        bookRepository.delete(book);
    }

    // Hämtar bok genom id 
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found."));
    }

    // Lägger till en genra till en bok
    public Book addGenreToBook(Long bookId, Long genreId) {
        Book book = getBookById(bookId);
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Genre not found."));
        book.getGenres().add(genre);
        return bookRepository.save(book);
    }

    // Söker book på titel och förfatare
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
