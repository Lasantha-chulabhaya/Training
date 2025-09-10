package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Entity.Borrowing;
import com.LibraryMGT.Training.Model.Entity.Member;
import jakarta.transaction.Transactional;
import org.modelmapper.internal.util.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT b FROM Borrowing b JOIN FETCH b.member m JOIN FETCH b.book bk WHERE m.memberId = :memberId")
    List<Borrowing> findBorrowingsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT b FROM Borrowing b JOIN FETCH b.member m JOIN FETCH b.book bk WHERE m.memberId = :memberId AND b.returnDate IS NULL")
    List<Borrowing> findCurrentBorrowingsByMemberId(@Param("memberId") Long memberId);

    Member findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.status = 'ACTIVE'")
    List<Member> findAllActive();
}
