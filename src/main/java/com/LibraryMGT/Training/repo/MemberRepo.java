package com.LibraryMGT.Training.repo;

import com.LibraryMGT.Training.Model.Entity.Member;
import org.modelmapper.internal.util.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
}
