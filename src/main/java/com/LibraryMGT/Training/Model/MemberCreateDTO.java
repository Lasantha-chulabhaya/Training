package com.LibraryMGT.Training.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCreateDTO {
    private String name;
    private String email;
    private String phone;
}
