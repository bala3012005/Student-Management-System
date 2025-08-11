package com.registration.studentmanagementsystem.Repository;

import com.registration.studentmanagementsystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {}
