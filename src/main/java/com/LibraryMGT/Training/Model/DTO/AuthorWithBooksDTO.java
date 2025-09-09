package com.LibraryMGT.Training.Model.DTO;

import java.util.List;

public class AuthorWithBooksDTO {
    private Long authorId;
    private String name;
    private String email;
    private Integer birthYear;
    private List<BookSummaryDTO> books;

    public AuthorWithBooksDTO() {
    }

    public AuthorWithBooksDTO(Long authorId, String name, String email, Integer birthYear, List<BookSummaryDTO> books) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.birthYear = birthYear;
        this.books = books;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<BookSummaryDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookSummaryDTO> books) {
        this.books = books;
    }
}
