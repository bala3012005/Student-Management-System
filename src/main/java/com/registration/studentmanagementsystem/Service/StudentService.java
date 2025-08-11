package com.registration.studentmanagementsystem.Service;

import com.registration.studentmanagementsystem.DTO.StudentPatchDTO;
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

    public void addStudents(List<StudentRequestDTO> studentRequestDTOs) {
        List<Student> students = new java.util.ArrayList<>();
        for (StudentRequestDTO dto : studentRequestDTOs) {
            Student student = new Student();
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setPassword(dto.getPassword());
            student.setCourse(dto.getCourse());
            student.setFees(dto.getFees());
            students.add(student);
        }
        studentRepo.saveAll(students);
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

    public void updateStudentPartial(int id, StudentPatchDTO studentPatchDTO) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found with id: " + id));

        if (studentPatchDTO.getName() != null)
            student.setName(studentPatchDTO.getName());
        if (studentPatchDTO.getEmail() != null)
            student.setEmail(studentPatchDTO.getEmail());
        if (studentPatchDTO.getPassword() != null)
            student.setPassword(studentPatchDTO.getPassword());
        if (studentPatchDTO.getCourse() != null)
            student.setCourse(studentPatchDTO.getCourse());
        if (studentPatchDTO.getFees() != null)
            student.setFees(studentPatchDTO.getFees());

        studentRepo.save(student);
    }

    public void deleteStudent(int id) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        studentRepo.deleteById(id);
    }
}
