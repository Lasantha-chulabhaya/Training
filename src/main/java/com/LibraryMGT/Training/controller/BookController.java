package com.LibraryMGT.Training.controller;

import com.LibraryMGT.Training.Model.AuthorWithBooksDTO;
import com.LibraryMGT.Training.Model.BookCreateDTO;
import com.LibraryMGT.Training.Model.BookDTO;
import com.LibraryMGT.Training.Service.AuthorService;
import com.LibraryMGT.Training.Service.BookService;
import com.LibraryMGT.Training.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create-book")
    public ResponseEntity<StandardResponse> createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        BookDTO createdBook = bookService.createBook(bookCreateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "saved successfully", createdBook),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/books")
    public ResponseEntity<StandardResponse> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "saved successfully", books),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBookById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "saved successfully", book),
                HttpStatus.OK
        );
    }
    @GetMapping("/available")
    public ResponseEntity<StandardResponse> getAvailableBooks() {
        List<BookDTO> books = bookService.getAvailableBooks();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "saved successfully", books),
                HttpStatus.OK
        );
    }
}