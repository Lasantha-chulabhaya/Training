package com.LibraryMGT.Training.Service.IMPL;

import com.LibraryMGT.Training.Model.DTO.*;
import com.LibraryMGT.Training.Model.Entity.Author;
import com.LibraryMGT.Training.Model.Entity.Book;
import com.LibraryMGT.Training.Model.Entity.Borrowing;
import com.LibraryMGT.Training.Model.Entity.Member;
import com.LibraryMGT.Training.Service.MemberService;
import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.repo.MemberRepo;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class MemberServiceIMPL implements MemberService {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<MemberDTO> getAllMembers() {

        List<Member> allMembers = memberRepo.findAll();

        if (allMembers.isEmpty()) {
            throw new NotFoundException("No Member found");
        }

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : allMembers) {
            MemberDTO memberDTO = new MemberDTO(
                    member.getMemberId(),
                    member.getName(),
                    member.getEmail(),
                    member.getPhone(),
                    member.getJoinDate(),
                    member.getStatus()
            );
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    @Override
    public MemberDTO createMember(MemberCreateDTO memberCreateDTO) {
        if (memberRepo.existsByEmail(memberCreateDTO.getEmail())) {
            throw new EntryDuplicateException("member with email " + memberCreateDTO.getEmail() + " already exists");
        }
        Member member = Member.builder()
                .name(memberCreateDTO.getName())
                .email(memberCreateDTO.getEmail())
                .phone(memberCreateDTO.getPhone())
                .joinDate(LocalDate.now())
                .status(Member.MemberStatus.ACTIVE)
                .build();

        Member saveMember = memberRepo.save(member);
        return modelMapper.map(saveMember, MemberDTO.class);
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        Member member = memberRepo.findById(id).get();
        if (member == null)
            throw new NotFoundException("Member with id " + id + " not found");
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .joinDate(member.getJoinDate())
                .status(member.getStatus())
                .build();
        return memberDTO;
    }

    @Override
    @Transactional
    public List<BorrowingDTO> getMemberBorrowings(Long id) {
        // First check if member exists
        if (!memberRepo.existsById(id)) {
            throw new NotFoundException("Member with id " + id + " not found");
        }

        // Query borrowings directly using the new repository method
        List<Borrowing> borrowings = memberRepo.findBorrowingsByMemberId(id);

        if (borrowings.isEmpty()) {
            throw new NotFoundException("Member with id " + id + " has no borrowings");
        }

        List<BorrowingDTO> borrowingDTOList = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            // Build MemberDTO from the borrowing's member (already fetched via JOIN FETCH)
            Member member = borrowing.getMember();
            MemberDTO memberDTO = MemberDTO.builder()
                    .memberId(member.getMemberId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .joinDate(member.getJoinDate())
                    .status(member.getStatus())
                    .build();

            // Build BookDTO from the borrowing's book (already fetched via JOIN FETCH)
            Book book = borrowing.getBook();
            BookDTO bookDTO = null;
            if (book != null) {
                bookDTO = BookDTO.builder()
                        .bookId(book.getBookId())
                        .isbn(book.getIsbn())
                        .title(book.getTitle())
                        .publicationYear(book.getPublicationYear())
                        .totalCopies(book.getTotalCopies())
                        .availableCopies(book.getAvailableCopies())
                        .status(book.getStatus())
                        .build();
            }

            // Build BorrowingDTO
            BorrowingDTO borrowingDTO = BorrowingDTO.builder()
                    .borrowId(borrowing.getBorrowId())
                    .member(memberDTO)
                    .book(bookDTO)
                    .borrowDate(borrowing.getBorrowDate())
                    .dueDate(borrowing.getDueDate())
                    .returnDate(borrowing.getReturnDate())
                    .status(borrowing.getStatus())
                    .build();

            borrowingDTOList.add(borrowingDTO);
        }

        return borrowingDTOList;
    }

    @Override
    public List<BorrowingDTO> getMemberCurrentBorrowings(Long id) {
        // First check if member exists
        if (!memberRepo.existsById(id)) {
            throw new NotFoundException("Member with id " + id + " not found");
        }

        // Query borrowings directly using the new repository method
        List<Borrowing> borrowings = memberRepo.findCurrentBorrowingsByMemberId(id);

        if (borrowings.isEmpty()) {
            throw new NotFoundException("Member with id " + id + " has no borrowings");
        }

        List<BorrowingDTO> borrowingDTOList = new ArrayList<>();

        for (Borrowing borrowing : borrowings) {
            // Build MemberDTO from the borrowing's member (already fetched via JOIN FETCH)
            Member member = borrowing.getMember();
            MemberDTO memberDTO = MemberDTO.builder()
                    .memberId(member.getMemberId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .joinDate(member.getJoinDate())
                    .status(member.getStatus())
                    .build();

            // Build BookDTO from the borrowing's book (already fetched via JOIN FETCH)
            Book book = borrowing.getBook();
            BookDTO bookDTO = null;
            if (book != null) {
                bookDTO = BookDTO.builder()
                        .bookId(book.getBookId())
                        .isbn(book.getIsbn())
                        .title(book.getTitle())
                        .publicationYear(book.getPublicationYear())
                        .totalCopies(book.getTotalCopies())
                        .availableCopies(book.getAvailableCopies())
                        .status(book.getStatus())
                        .build();
            }

            // Build BorrowingDTO
            BorrowingDTO borrowingDTO = BorrowingDTO.builder()
                    .borrowId(borrowing.getBorrowId())
                    .member(memberDTO)
                    .book(bookDTO)
                    .borrowDate(borrowing.getBorrowDate())
                    .dueDate(borrowing.getDueDate())
                    .returnDate(borrowing.getReturnDate())
                    .status(borrowing.getStatus())
                    .build();

            borrowingDTOList.add(borrowingDTO);
        }

        return borrowingDTOList;
    }

    @Override
    public MemberDTO searchMemberByEmail(String email) {
        Member member = memberRepo.findByEmail(email);
        if (member == null) {
            throw new NotFoundException("Member with email " + email + " not found");
        }
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .joinDate(member.getJoinDate())
                .status(member.getStatus())
                .build();
        return memberDTO;

    }

    @Override
    public List<MemberDTO> getActiveMembers() {
        List<Member> members = memberRepo.findAllActive();
        if (members.isEmpty()) {
            throw new NotFoundException("No active members found");
        }
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (Member member : members) {
            MemberDTO memberDTO = new MemberDTO(
                    member.getMemberId(),
                    member.getName(),
                    member.getEmail(),
                    member.getPhone(),
                    member.getJoinDate(),
                    member.getStatus()
            );
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;

    }

    @Override
    public MemberDTO updateMember(Long id, MemberCreateDTO memberCreateDTO) {
        Member member = memberRepo.findById(id).get();
        if (member == null) {
            throw new NotFoundException("Member with id " + id + " not found");
        }
        member.setName(memberCreateDTO.getName());
        member.setEmail(memberCreateDTO.getEmail());
        member.setPhone(memberCreateDTO.getPhone());

        Member updatedmember = memberRepo.save(member);
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(updatedmember.getMemberId())
                .name(updatedmember.getName())
                .email(updatedmember.getEmail())
                .build();
        return memberDTO;
    }
}

