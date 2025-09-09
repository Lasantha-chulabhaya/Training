package com.LibraryMGT.Training.Model.DTO;

import java.util.Set;
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class BookCreateDTO {

    private String isbn;
    private String title;
    private Integer publicationYear;
    private Integer totalCopies;
    private Integer availableCopies;
    private Long categoryId;
    private Set<Long> authorIds;

    public BookCreateDTO() {
    }

    public BookCreateDTO(String isbn, String title, Integer publicationYear, Integer totalCopies, Integer availableCopies, Long categoryId, Set<Long> authorIds) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.categoryId = categoryId;
        this.authorIds = authorIds;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Set<Long> authorIds) {
        this.authorIds = authorIds;
    }
}
