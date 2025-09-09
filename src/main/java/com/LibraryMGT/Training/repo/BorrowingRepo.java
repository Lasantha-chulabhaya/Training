package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepo extends JpaRepository<Borrowing, Long> {
}
