package com.example.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarysystem.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByBookBookId(Long bookId);

    List<Loan> findByBorrowerUserId(Long borrowerId);

    List<Loan> findByReturnedDateIsNull(); // For active loans
}
