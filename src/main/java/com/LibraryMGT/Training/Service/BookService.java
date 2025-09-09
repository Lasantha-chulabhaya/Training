package com.LibraryMGT.Training.Service;

import com.LibraryMGT.Training.Model.DTO.BookCreateDTO;
import com.LibraryMGT.Training.Model.DTO.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookCreateDTO bookCreateDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    List<BookDTO> getAvailableBooks();

    List<BookDTO> searchBooksByTitle(String title);

    BookDTO searchBookByIsbn(String isbn);
}
