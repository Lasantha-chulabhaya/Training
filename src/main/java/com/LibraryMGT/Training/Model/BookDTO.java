package com.LibraryMGT.Training.Model;

import java.util.List;

public class BookDTO {

    private Long bookId;
    private String isbn;
    private String title;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;
    private Book.BookStatus status;
    private CategoryDTO category;
    private List<AuthorDTO> authors;

    public BookDTO() {
    }

    public BookDTO(Long bookId, String isbn, String title, Integer publicationYear, Integer totalCopies, Integer availableCopies, Book.BookStatus status, CategoryDTO category, List<AuthorDTO> authors) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.status = status;
        this.category = category;
        this.authors = authors;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Book.BookStatus getStatus() {
        return status;
    }

    public void setStatus(Book.BookStatus status) {
        this.status = status;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
