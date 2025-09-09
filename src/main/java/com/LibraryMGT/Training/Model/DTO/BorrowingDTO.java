package com.LibraryMGT.Training.Model.DTO;

import com.LibraryMGT.Training.Model.Entity.Borrowing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BorrowingDTO {
    private Long borrowId;
    private MemberDTO member;
    private BookDTO book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Borrowing.BorrowingStatus status;

}
