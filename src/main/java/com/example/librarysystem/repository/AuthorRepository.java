package com.example.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarysystem.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Kan lägga till flera querry om det behövs
}
