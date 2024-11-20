package com.example.librarysystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarysystem.entity.Borrower;
import com.example.librarysystem.entity.Loan;
import com.example.librarysystem.service.BorrowerService;
import com.example.librarysystem.service.LoanService;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;
    private final LoanService loanService;

    public BorrowerController(BorrowerService borrowerService, LoanService loanService) {
        this.borrowerService = borrowerService;
        this.loanService = loanService;
    }

    // Regristerar en ny lånare
    @PostMapping
    public Borrower addBorrower(@RequestBody Borrower borrower) {
        return borrowerService.addBorrower(borrower);
    }

    // Updaterar lånare information
    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable Long id,
                                   @RequestBody Borrower borrowerDetails) {
        return borrowerService.updateBorrower(id, borrowerDetails);
    }

    // Hämtar alla aktiv lånare
    @GetMapping("/{id}/loans")
    public List<Loan> getBorrowerLoans(@PathVariable Long id) {
        return loanService.getLoansByBorrower(id);
    }
}
