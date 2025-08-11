package com.registration.studentmanagementsystem.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private String name;
    private String email;
    private String course;
    private long fees;

    // You can add more fields if needed, such as id or other relevant information
}
