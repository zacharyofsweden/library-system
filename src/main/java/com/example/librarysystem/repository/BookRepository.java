package com.example.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.librarysystem.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

      List<Book> findByTitleContainingIgnoreCase(String title);


    List<Book> findByAuthorFirstNameContainingIgnoreCase(String firstName);

    List<Book> findByAuthorLastNameContainingIgnoreCase(String lastName);

     @Query("SELECT b FROM Book b WHERE LOWER(CONCAT(b.author.firstName, ' ', b.author.lastName)) LIKE LOWER(CONCAT('%', :authorName, '%'))")
    List<Book> findByAuthorFullNameContainingIgnoreCase(@Param("authorName") String authorName);

    @EntityGraph(attributePaths = {"genres", "author"})
    @Query("SELECT DISTINCT b FROM Book b JOIN b.genres g WHERE LOWER(g.name) = LOWER(:genre)")
    List<Book> findByGenreName(@Param("genre") String genre);

}
