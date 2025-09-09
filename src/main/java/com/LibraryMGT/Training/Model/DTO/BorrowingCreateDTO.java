package com.LibraryMGT.Training.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowingCreateDTO {

    private Long memberId;
    private Long bookId;

}
