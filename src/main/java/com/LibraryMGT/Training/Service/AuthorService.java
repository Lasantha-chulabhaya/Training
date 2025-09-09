package com.LibraryMGT.Training.Service;

import com.LibraryMGT.Training.Model.DTO.AuthorCreateDTO;
import com.LibraryMGT.Training.Model.DTO.AuthorDTO;
import com.LibraryMGT.Training.Model.DTO.AuthorWithBooksDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO);

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthorById(Long id);

    AuthorWithBooksDTO getAuthorWithBooks(Long id);

    List<AuthorDTO> searchAuthorsByName(String name);
}
