package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.status = 'ACTIVE'")
    List<Book> findAllActive();

    List<Book> findAllByTitle(String title);

    Book findByIsbn(String isbn);
}
