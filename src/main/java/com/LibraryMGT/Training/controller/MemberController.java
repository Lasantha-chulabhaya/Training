package com.LibraryMGT.Training.controller;

import com.LibraryMGT.Training.Model.DTO.BorrowingDTO;
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
                new StandardResponse(201, "Success", createdMember),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getMemberById(@PathVariable Long id) {
        MemberDTO member = memberService.getMemberById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", member),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}/borrowings")
    public ResponseEntity<StandardResponse> getMemberBorrowings(@PathVariable Long id) {
        List<BorrowingDTO> borrowings = memberService.getMemberBorrowings(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", borrowings),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}/current-borrowings")
    public ResponseEntity<StandardResponse> getMemberCurrentBorrowings(@PathVariable Long id) {
        List<BorrowingDTO> borrowings = memberService.getMemberCurrentBorrowings(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", borrowings),
                HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<StandardResponse> searchMemberByEmail(@RequestParam String email) {
        MemberDTO member = memberService.searchMemberByEmail(email);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", member),
                HttpStatus.OK
        );
    }

    @GetMapping("/active")
    public ResponseEntity<StandardResponse> getActiveMembers() {
        List<MemberDTO> members = memberService.getActiveMembers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", members),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateMember(@PathVariable Long id,
                                                  @RequestBody MemberCreateDTO memberCreateDTO) {
        MemberDTO updatedMember = memberService.updateMember(id, memberCreateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", updatedMember),
                HttpStatus.OK
        );
    }
}
