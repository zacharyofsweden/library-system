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

    public LoanService(LoanRepository loanRepository, BookService bookService,
            BorrowerService borrowerService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    // Tar ut en bok
    public Loan checkoutBook(Long bookId, Long borrowerId) {
        Book book = bookService.getBookById(bookId);
        if (book.isCheckedOut()) {
            throw new RuntimeException("Book is already checked out.");
        }

        Borrower borrower = borrowerService.getBorrowerById(borrowerId);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(borrower);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusWeeks(2));

        book.setCheckedOut(true);
        bookService.updateBook(bookId, book);

        return loanRepository.save(loan);
    }

    // returnera en bok
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found."));
        Book book = loan.getBook();
        book.setCheckedOut(false);
        bookService.updateBook(book.getBookId(), book);
        loan.setActive(false);
        loanRepository.save(loan);
        return loan;
    }

    // Example of using the updated repository method
    public List<Loan> getLoansByBookId(Long bookId) {
        return loanRepository.findByBookBookId(bookId);
        
    }

    // hämtar all nuvarande lån
    public List<Loan> getActiveLoans() {
        return loanRepository.findAll();
    }

    // hämtar alla lån genom dom som lånar
    public List<Loan> getLoansByBorrower(Long borrowerId) {
        return loanRepository.findByBorrowerId(borrowerId);
    }
}
