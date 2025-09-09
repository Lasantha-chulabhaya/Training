package com.LibraryMGT.Training.Model.DTO;

import com.LibraryMGT.Training.Model.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDTO {

    private Long memberId;
    private String name;
    private String email;
    private String phone;
    private LocalDate joinDate;
    private Member.MemberStatus status;
}
