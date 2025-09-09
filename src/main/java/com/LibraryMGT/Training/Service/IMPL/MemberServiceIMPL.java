package com.LibraryMGT.Training.Service.IMPL;

import com.LibraryMGT.Training.Model.DTO.AuthorDTO;
import com.LibraryMGT.Training.Model.DTO.MemberCreateDTO;
import com.LibraryMGT.Training.Model.DTO.MemberDTO;
import com.LibraryMGT.Training.Model.Entity.Author;
import com.LibraryMGT.Training.Model.Entity.Member;
import com.LibraryMGT.Training.Service.MemberService;
import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.repo.MemberRepo;
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
//        Author author = new Author();
//        author.setName(authorCreateDTO.getName());
//        author.setEmail(authorCreateDTO.getEmail());
//        author.setBirthYear(authorCreateDTO.getBirthYear());

        Member saveMember = memberRepo.save(member);
        return modelMapper.map(saveMember, MemberDTO.class);
    }
}

