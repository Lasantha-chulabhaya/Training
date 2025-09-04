package com.LibraryMGT.Training.Service;

import com.LibraryMGT.Training.Model.BookCreateDTO;
import com.LibraryMGT.Training.Model.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookCreateDTO bookCreateDTO);

    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    List<BookDTO> getAvailableBooks();

    List<BookDTO> searchBooksByTitle(String title);

    BookDTO searchBookByIsbn(String isbn);
}
