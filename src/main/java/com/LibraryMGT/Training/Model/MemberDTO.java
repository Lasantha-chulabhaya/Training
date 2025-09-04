package com.LibraryMGT.Training.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {

    private Long memberId;
    private String name;
    private String email;
    private String phone;
    private LocalDate joinDate;
    private Member.MemberStatus status;
}
