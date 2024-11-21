package com.example.librarysystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Borrower;
import com.example.librarysystem.repository.BorrowerRepository;

@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    // Lägger till en ny lånare 
    public Borrower addBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    // updaterar lånaren
    public Borrower updateBorrower(Long id, Borrower borrowerDetails) {
        Borrower borrower = getBorrowerById(id);
        borrower.setFirstName(borrowerDetails.getFirstName());
        borrower.setLastName(borrowerDetails.getLastName());
        borrower.setEmail(borrowerDetails.getEmail());
        return borrowerRepository.save(borrower);
    }

    // hämtar en lånare baserad på id 
   
    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrower not found."));
    }

    // Hämtar all lånare
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }
}
