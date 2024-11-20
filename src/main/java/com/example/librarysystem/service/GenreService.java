package com.example.librarysystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Genre;
import com.example.librarysystem.repository.GenreRepository;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    // Konstruktur 
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Hämta alla genras
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Hämta genra från id 
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found."));
    }

    // Hämta en ny id
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Uppdatera en genra
    public Genre updateGenre(Long id, Genre genreDetails) {
        Genre genre = getGenreById(id);
        genre.setName(genreDetails.getName());
        return genreRepository.save(genre);
    }

    // Tar bort en genra
    public void deleteGenre(Long id) {
        Genre genre = getGenreById(id);
        genreRepository.delete(genre);
    }
}
