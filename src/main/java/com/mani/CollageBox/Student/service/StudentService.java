package com.mani.CollageBox.Student.service;

import com.mani.CollageBox.Student.entity.Student;
import com.mani.CollageBox.Student.entityImpl.StudentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    @Autowired
    private StudentImpl studentImpl;

    @Autowired
    private Student student;

    public Student getStudent(int id){
        try {
            return studentImpl.getStudent(id);
        }catch (Exception e){
            return null;
        }
    }

    public List<Student> getAllStudent() {
        return studentImpl.getAllStudent();
    }

    public boolean checkLogIn(Student student){
        Student studentTemp;
        try{
            studentTemp = studentImpl.getStudent(student.getId());
        }catch (Exception e){
            return false;
        }
        if(studentTemp.getPassword().equals(student.getPassword())){
            return true;
        }
        return false;
    }

    public boolean studentDelete(int id){
        try{
            studentImpl.studentDelete(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void addStudent(Student student){
        studentImpl.addStudent(student);
    }

    public int getId(){
        return student.getId();
    }

}
