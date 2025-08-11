package com.registration.studentmanagementsystem.Controller;

import com.registration.studentmanagementsystem.DTO.StudentRequestDTO;
import com.registration.studentmanagementsystem.DTO.StudentResponseDTO;
import com.registration.studentmanagementsystem.Service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
    
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        studentService.addStudent(studentRequestDTO);
        return ResponseEntity.ok("Student added successfully");
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        studentService.updateStudent(id, studentRequestDTO);
        return ResponseEntity.ok("Student updated successfully");
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<String> updateStudentPartial(@PathVariable int id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        studentService.updateStudentPartial(id, studentRequestDTO);
        return ResponseEntity.ok("Student updated successfully");
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
