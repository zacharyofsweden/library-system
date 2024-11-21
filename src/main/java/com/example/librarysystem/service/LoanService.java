package com.example.librarysystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.librarysystem.entity.Book;
import com.example.librarysystem.entity.Borrower;
import com.example.librarysystem.entity.Loan;
import com.example.librarysystem.repository.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final BorrowerService borrowerService;

    // Konstruktur
    public LoanService(LoanRepository loanRepository, BookService bookService,
            BorrowerService borrowerService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    // Checkout en bok
    public Loan checkoutBook(Long bookId, Long borrowerId) {
        Book book = bookService.getBookById(bookId);
        if (!book.getAvailable()) {
            throw new RuntimeException("Book is already checked out.");
        }

        // Hämtar lånare 
        Borrower borrower = borrowerService.getBorrowerById(borrowerId);

        if (borrower == null) {
            throw new RuntimeException("Borrower not found.");
        }

        
        book.setAvailable(true);
        bookService.updateBook(bookId, book);

        // Create a new loan
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(borrower);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusWeeks(2));
        loan.setReturnedDate(null);

        book.setAvailable(false);
        bookService.updateBook(bookId, book);

        return loanRepository.save(loan);
    }

    // Retuera en bok
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found."));
        if (loan.getReturnedDate() != null) {
            throw new RuntimeException("Book has already been returned.");
        }

        // Sätter ett retur datum
        loan.setReturnedDate(LocalDate.now());
        loanRepository.save(loan);

        // Uppdatera bookes avilibilitet
        Book book = loan.getBook();
        book.setAvailable(true);
        bookService.updateBook(book.getBookId(), book);

        return loan;
    }

    //Hämta alla lån baserat på bok id
    public List<Loan> getLoansByBookId(Long bookId) {
        return loanRepository.findByBookBookId(bookId);
    }

    // Hämtar alla aktiva lån
    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnedDateIsNull();// Consider filtering by active loans
    }

    // Hämta alla lån baserat på användare id 
    public List<Loan> getLoansByBorrower(Long borrowerId) {
        return loanRepository.findByBorrowerUserId(borrowerId);
    }
}
