package com.mani.CollageBox.Student.entityImpl;

import com.mani.CollageBox.Student.entity.Student;
import com.mani.CollageBox.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentImpl {

    @Autowired
    StudentRepository repository;

    public Student getStudent(int id){
        System.out.println(id);
        return repository.findById(id).orElse(null);
    }

    public List<Student> getAllStudent(){
        return repository.findAll();
    }

    public void addStudent(Student student){
        repository.save(student);
    }

    public void studentDelete(int id){
        repository.deleteById(id);
    }

}
