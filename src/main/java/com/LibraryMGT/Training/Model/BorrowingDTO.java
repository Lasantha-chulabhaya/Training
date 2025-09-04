package com.LibraryMGT.Training.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowingDTO {
    private Long borrowId;
    private MemberDTO member;
    private BookDTO book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Borrowing.BorrowingStatus status;

}
