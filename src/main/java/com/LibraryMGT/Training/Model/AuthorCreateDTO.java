package com.LibraryMGT.Training.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AuthorCreateDTO {

    private String name;
    private String email;
    private Integer birthYear;

    public AuthorCreateDTO() {
    }

    public AuthorCreateDTO(String name, String email, Integer birthYear) {
        this.name = name;
        this.email = email;
        this.birthYear = birthYear;
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
