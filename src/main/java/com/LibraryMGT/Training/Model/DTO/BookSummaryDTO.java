package com.LibraryMGT.Training.Model.DTO;

public class BookSummaryDTO {
    private Long bookId;
    private String isbn;
    private String title;
    private Integer publicationYear;
    private Integer availableCopies;

    public BookSummaryDTO() {
    }

    public BookSummaryDTO(Long bookId, String isbn, String title, Integer publicationYear, Integer availableCopies) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.availableCopies = availableCopies;
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

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
