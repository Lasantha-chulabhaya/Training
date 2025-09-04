package com.LibraryMGT.Training.controller;

import com.LibraryMGT.Training.Model.AuthorCreateDTO;
import com.LibraryMGT.Training.Model.AuthorDTO;
import com.LibraryMGT.Training.Model.AuthorWithBooksDTO;
import com.LibraryMGT.Training.Service.AuthorService;
import com.LibraryMGT.Training.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<StandardResponse> createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO) {
        AuthorDTO createdAuthor = authorService.createAuthor(authorCreateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "saved successfully", createdAuthor),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-authors")
    public ResponseEntity<StandardResponse> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", authors),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", author),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<StandardResponse> getAuthorWithBooks(@PathVariable Long id) {
        AuthorWithBooksDTO authorWithBooks = authorService.getAuthorWithBooks(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", authorWithBooks),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<StandardResponse> searchAuthorsByName(@RequestParam String name) {
        List<AuthorDTO> authors = authorService.searchAuthorsByName(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", authors),
                HttpStatus.OK
        );
    }
}
