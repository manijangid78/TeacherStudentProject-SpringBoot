package com.mani.CollageBox.Student.repository;

import com.mani.CollageBox.Student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
