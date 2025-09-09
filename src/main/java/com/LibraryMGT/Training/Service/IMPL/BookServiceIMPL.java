package com.LibraryMGT.Training.Service.IMPL;

import com.LibraryMGT.Training.Model.DTO.AuthorDTO;
import com.LibraryMGT.Training.Model.DTO.BookCreateDTO;
import com.LibraryMGT.Training.Model.DTO.BookDTO;
import com.LibraryMGT.Training.Model.DTO.CategoryDTO;
import com.LibraryMGT.Training.Model.Entity.Author;
import com.LibraryMGT.Training.Model.Entity.Book;
import com.LibraryMGT.Training.Model.Entity.Category;
import com.LibraryMGT.Training.Service.BookService;
import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.repo.AuthorRepo;
import com.LibraryMGT.Training.repo.BookRepo;
import com.LibraryMGT.Training.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceIMPL implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    CategoryRepo categoryRepo;


    @Override
    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        if (bookRepo.existsByIsbn(bookCreateDTO.getIsbn())) {
//            if (bookRepo.existsById(bookCreateDTO.getCategoryId()))
            throw new EntryDuplicateException("Book with ISBN " + bookCreateDTO.getIsbn() + " already exists");
        }

        Book book = new Book();
        book.setIsbn(bookCreateDTO.getIsbn());
        book.setTitle(bookCreateDTO.getTitle());
        book.setPublicationYear(bookCreateDTO.getPublicationYear());
        book.setTotalCopies(bookCreateDTO.getTotalCopies());
        book.setAvailableCopies(bookCreateDTO.getAvailableCopies());
        book.setStatus(Book.BookStatus.ACTIVE);

//          Book book = modelMapper.map(bookCreateDTO, Book.class);
//            return modelMapper.map(book, BookDTO.class);

        Optional<Category> optionalCategory = categoryRepo.findById(bookCreateDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new NotFoundException("Category not found");
        } else {
            Category category = optionalCategory.get();
            book.setCategory(category);

            Set<Author> authors = new HashSet<>(authorRepo.findAllById(bookCreateDTO.getAuthorIds()));
            book.setAuthors(authors);

            book = bookRepo.save(book);


            //To make the return data DTO
            BookDTO retbook = new BookDTO();
            retbook.setBookId(book.getBookId());
            retbook.setIsbn(book.getIsbn());
            retbook.setTitle(book.getTitle());
            retbook.setPublicationYear(book.getPublicationYear());
            retbook.setTotalCopies(book.getTotalCopies());
            retbook.setAvailableCopies(book.getAvailableCopies());
            retbook.setStatus(book.getStatus());

            // map category if it exists
            if (book.getCategory() != null) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(book.getCategory().getCategoryId());
                categoryDTO.setName(book.getCategory().getName());
                categoryDTO.setDescription(book.getCategory().getDescription());
                retbook.setCategory(categoryDTO);
            }

            // map authors if they exist
            if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
                List<AuthorDTO> authorDTOs = new ArrayList<>();
                for (Author author : book.getAuthors()) {
                    AuthorDTO authorDTO = new AuthorDTO();
                    authorDTO.setAuthorId(author.getAuthorId());
                    authorDTO.setName(author.getName());
                    authorDTO.setEmail(author.getEmail());
                    authorDTO.setBirthYear(author.getBirthYear());
                    authorDTOs.add(authorDTO);
                }
                retbook.setAuthors(authorDTOs);


            }
            return retbook;


        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        if (books.isEmpty()) {
            throw new NotFoundException("No books found");
        }
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookId(book.getBookId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setPublicationYear(book.getPublicationYear());
            bookDTO.setTotalCopies(book.getTotalCopies());
            bookDTO.setAvailableCopies(book.getAvailableCopies());
            bookDTO.setStatus(book.getStatus());


            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(book.getCategory().getCategoryId());
            categoryDTO.setName(book.getCategory().getName());
            categoryDTO.setDescription(book.getCategory().getDescription());

            bookDTO.setCategory(categoryDTO);

            List<AuthorDTO> authorDTOs = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setAuthorId(author.getAuthorId());
                authorDTO.setName(author.getName());
                authorDTO.setEmail(author.getEmail());
                authorDTO.setBirthYear(author.getBirthYear());
                authorDTOs.add(authorDTO);

                bookDTO.setAuthors(authorDTOs);

                bookDTOs.add(bookDTO);
            }

        }
        return bookDTOs;
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepo.findById(id).get();
        if (book == null) {
            throw new NotFoundException("Book not found");
        }
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;


    }

    @Override
    public List<BookDTO> getAvailableBooks() {
        List<Book> books = bookRepo.findAllActive();
        if (books.isEmpty()) {
            throw new NotFoundException("No books found");
        }
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setBookId(book.getBookId());
            bookDTO.setIsbn(book.getIsbn());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setPublicationYear(book.getPublicationYear());
            bookDTO.setTotalCopies(book.getTotalCopies());
            bookDTO.setAvailableCopies(book.getAvailableCopies());
            bookDTO.setStatus(book.getStatus());


            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(book.getCategory().getCategoryId());
            categoryDTO.setName(book.getCategory().getName());
            categoryDTO.setDescription(book.getCategory().getDescription());

            bookDTO.setCategory(categoryDTO);

            List<AuthorDTO> authorDTOs = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setAuthorId(author.getAuthorId());
                authorDTO.setName(author.getName());
                authorDTO.setEmail(author.getEmail());
                authorDTO.setBirthYear(author.getBirthYear());
                authorDTOs.add(authorDTO);

                bookDTO.setAuthors(authorDTOs);

                bookDTOs.add(bookDTO);
            }

        }
        return bookDTOs;

    }

    @Override
    public List<BookDTO> searchBooksByTitle(String title) {
       List<Book> books =  bookRepo.findAllByTitle(title);
       if (books.isEmpty()) {
           throw new NotFoundException("No books found");
       }
       List<BookDTO> bookDTOs = new ArrayList<>();
       for (Book book : books) {
           BookDTO bookDTO = new BookDTO();
           bookDTO.setBookId(book.getBookId());
           bookDTO.setIsbn(book.getIsbn());
           bookDTO.setTitle(book.getTitle());
           bookDTO.setPublicationYear(book.getPublicationYear());
           bookDTO.setTotalCopies(book.getTotalCopies());
           bookDTO.setAvailableCopies(book.getAvailableCopies());
           bookDTO.setStatus(book.getStatus());

           CategoryDTO categoryDTO = new CategoryDTO();
           categoryDTO.setCategoryId(book.getCategory().getCategoryId());
           categoryDTO.setName(book.getCategory().getName());
           categoryDTO.setDescription(book.getCategory().getDescription());
           bookDTO.setCategory(categoryDTO);

           List<AuthorDTO> authorDTOs = new ArrayList<>();
           for (Author author : book.getAuthors()) {
               AuthorDTO authorDTO = new AuthorDTO();
               authorDTO.setAuthorId(author.getAuthorId());
               authorDTO.setName(author.getName());
               authorDTO.setEmail(author.getEmail());
               authorDTO.setBirthYear(author.getBirthYear());
               authorDTOs.add(authorDTO);
               bookDTO.setAuthors(authorDTOs);
               bookDTOs.add(bookDTO);

           }
       }
       return bookDTOs;

    }

    @Override
    public BookDTO searchBookByIsbn(String isbn) {
        Book book = bookRepo.findByIsbn(isbn);
        if (book == null) {
            throw new NotFoundException("Book not found");
        }
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return bookDTO;
    }
}
