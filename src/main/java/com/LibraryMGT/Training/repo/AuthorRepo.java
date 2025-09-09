package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.authorId = :id")
    Optional<Author> findByIdWithBooks(@Param("id") Long id);

    List<Object> findAllBooksByAuthorId(Long authorId);

    Author findByName(String name);
}
