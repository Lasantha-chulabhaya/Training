package com.LibraryMGT.Training.Model.DTO;

import lombok.Builder;

import java.util.List;

//@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class AuthorDTO {

    private Long authorId;
    private String name;
    private String email;
    private Integer birthYear;


    public AuthorDTO() {
    }

    public AuthorDTO(Long authorId, String name, String email, Integer birthYear, List<BookDTO> books) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.birthYear = birthYear;

    }

    public AuthorDTO(Long authorId, String name, String email, Integer birthYear) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.birthYear = birthYear;
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




}
