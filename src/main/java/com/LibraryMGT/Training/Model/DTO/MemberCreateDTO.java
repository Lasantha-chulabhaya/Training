package com.LibraryMGT.Training.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberCreateDTO {
    private String name;
    private String email;
    private String phone;
}
