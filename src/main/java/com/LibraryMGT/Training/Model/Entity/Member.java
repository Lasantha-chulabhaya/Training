package com.LibraryMGT.Training.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "join_date")
    private LocalDate joinDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MemberStatus status = MemberStatus.ACTIVE;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Borrowing> borrowings;

    public enum MemberStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
}
