package com.LibraryMGT.Training.Service.IMPL;

import com.LibraryMGT.Training.Model.Author;
import com.LibraryMGT.Training.Model.AuthorCreateDTO;
import com.LibraryMGT.Training.Model.AuthorDTO;
import com.LibraryMGT.Training.Model.AuthorWithBooksDTO;
import com.LibraryMGT.Training.Service.AuthorService;
import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.repo.AuthorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceIMPL implements AuthorService {

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        if(authorRepo.existsByEmail(authorCreateDTO.getEmail())){
            throw new EntryDuplicateException("author with email " + authorCreateDTO.getEmail() + " already exists");
        }
        Author author = new Author();
        author.setName(authorCreateDTO.getName());
        author.setEmail(authorCreateDTO.getEmail());
        author.setBirthYear(authorCreateDTO.getBirthYear());

        Author savedAuthor = authorRepo.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> AllAuthors = authorRepo.findAll();
        if (AllAuthors.isEmpty()) {
            throw new NotFoundException(" No authors Found");
        } else {
            List<AuthorDTO> authorDTOList = new ArrayList<>();
            for (Author author : AllAuthors) {
                AuthorDTO authorDTO = new AuthorDTO(
                        author.getAuthorId(),
                        author.getName(),
                        author.getEmail(),
                        author.getBirthYear()
                );
                authorDTOList.add(authorDTO);

            }
            return authorDTOList;
        }
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        if(authorRepo.existsById(id)){
            Author author = authorRepo.findById(id).get();
            return modelMapper.map(author, AuthorDTO.class);
        }
        throw new NotFoundException(" No Author Found");

    }

    @Override
    public AuthorWithBooksDTO getAuthorWithBooks(Long id) {
        if(authorRepo.existsById(id)){
            Author author = authorRepo.findByIdWithBooks(id).get();
            return modelMapper.map(author, AuthorWithBooksDTO.class);
        }
        throw new NotFoundException(" No Author Found");
    }

    @Override
    public List<AuthorDTO> searchAuthorsByName(String name) {
        Author author = authorRepo.findByName(name);
        if (author == null) {
            throw new NotFoundException(" No Author Found");
        }
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        authorDTOList.add(modelMapper.map(author, AuthorDTO.class));
        return authorDTOList;

    }
}
