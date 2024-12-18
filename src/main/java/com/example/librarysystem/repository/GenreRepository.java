package com.example.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarysystem.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}