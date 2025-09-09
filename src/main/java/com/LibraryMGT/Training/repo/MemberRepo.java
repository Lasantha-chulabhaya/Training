package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Entity.Member;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.util.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    @Transactional
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.borrowings WHERE m.memberId = :id")
    Optional<Member> findByIdWithBorrowings(@Param("id") Long id);
}
