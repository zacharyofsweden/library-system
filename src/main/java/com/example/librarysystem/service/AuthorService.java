package com.example.librarysystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Author;
import com.example.librarysystem.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    // Konstruktur
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Hämta alla förfatare
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Hämta förfatare från id 
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found."));
    }

    // Lägg till en ny förfatare
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Upddatre en förfatare
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        author.setBirthDate(authorDetails.getBirthDate());
        return authorRepository.save(author);
    }

    // Tar bort en förfatare
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
