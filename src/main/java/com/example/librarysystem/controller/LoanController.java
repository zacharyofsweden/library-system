package com.example.librarysystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarysystem.entity.Loan;
import com.example.librarysystem.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // hämta en bok 
    @PostMapping("/checkout")
    public Loan checkoutBook(@RequestParam Long bookId, @RequestParam Long borrowerId) {
        return loanService.checkoutBook(bookId, borrowerId);
    }

    // returnerar en bok
    @PostMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }

    // hämtar alla aktiva lån
    @GetMapping
    public List<Loan> getActiveLoans() {
        return loanService.getActiveLoans();
    }
}
