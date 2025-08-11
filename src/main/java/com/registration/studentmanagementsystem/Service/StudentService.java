package com.registration.studentmanagementsystem.Service;

import com.registration.studentmanagementsystem.DTO.StudentRequestDTO;
import com.registration.studentmanagementsystem.DTO.StudentResponseDTO;
import com.registration.studentmanagementsystem.Model.Student;
import com.registration.studentmanagementsystem.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepo.findAll()
                .stream()
                .map(student -> new StudentResponseDTO(student.getName(), student.getEmail(), student.getCourse(), student.getFees()))
                .toList();
    }

    public void addStudent(StudentRequestDTO studentRequestDTO) {

        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setCourse(studentRequestDTO.getCourse());
        student.setFees(studentRequestDTO.getFees());

        // Save the student entity to the database
        studentRepo.save(student);
    }

    public StudentResponseDTO getStudentById(int id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        return new StudentResponseDTO(student.getName(), student.getEmail(), student.getCourse(), student.getFees());
    }

    public void updateStudent(int id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("student not found with id: " + id));

        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setCourse(studentRequestDTO.getCourse());
        student.setFees(studentRequestDTO.getFees());

        studentRepo.save(student);
    }

    public void updateStudentPartial(int id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found with id: " + id));

        if (studentRequestDTO.getName() != null) student.setName(studentRequestDTO.getName());
        if (studentRequestDTO.getEmail() != null) student.setEmail(studentRequestDTO.getEmail());
        if (studentRequestDTO.getPassword() != null) student.setPassword(studentRequestDTO.getPassword());
        if (studentRequestDTO.getCourse() != null) student.setCourse(studentRequestDTO.getCourse());
        if (studentRequestDTO.getFees() != null) student.setFees(studentRequestDTO.getFees());

        studentRepo.save(student);
    }

    public void deleteStudent(int id) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentRepo.deleteById(id);
    }
}
