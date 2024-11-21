package com.example.librarysystem.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "member_number", nullable = false, unique = true)
    private String memberNumber;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    @JsonIgnore // Prevent serialization of loans in the borrower
    private Set<Loan> loans;

    // Konstruktur
    public Borrower() {
    }

    public Borrower(String firstName, String lastName, String email, String memberNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.memberNumber = memberNumber;
    }

    // Getters and Setters
    // AnvändareId
    public Long getUserId() {
        return userId;
    }

    // AnvändareFörnamn
    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Användare EfterNamn
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Mail
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // MedlemsNumber
    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    // Lån
    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
