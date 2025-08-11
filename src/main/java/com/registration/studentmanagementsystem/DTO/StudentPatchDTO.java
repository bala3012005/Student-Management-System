package com.registration.studentmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPatchDTO {
    private String name;
    private String email;
    private String password;
    private String course;
    private Long fees;
}

