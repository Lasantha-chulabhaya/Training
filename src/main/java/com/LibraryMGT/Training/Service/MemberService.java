package com.LibraryMGT.Training.Service;

import com.LibraryMGT.Training.Model.DTO.MemberCreateDTO;
import com.LibraryMGT.Training.Model.DTO.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> getAllMembers();

    MemberDTO createMember(MemberCreateDTO memberCreateDTO);
}
