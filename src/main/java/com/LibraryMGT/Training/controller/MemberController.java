package com.LibraryMGT.Training.controller;

import com.LibraryMGT.Training.Model.DTO.MemberCreateDTO;
import com.LibraryMGT.Training.Model.DTO.MemberDTO;
import com.LibraryMGT.Training.Service.MemberService;
import com.LibraryMGT.Training.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<StandardResponse> getAllMembers() {
        List<MemberDTO> members = memberService.getAllMembers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", members),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createMember(@RequestBody MemberCreateDTO memberCreateDTO) {
        MemberDTO createdMember = memberService.createMember(memberCreateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", createdMember),
                HttpStatus.OK
        );
    }
}
