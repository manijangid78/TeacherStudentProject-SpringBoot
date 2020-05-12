package com.mani.CollageBox.Teacher.service;

import com.mani.CollageBox.Student.entity.Student;
import com.mani.CollageBox.Student.service.StudentService;
import com.mani.CollageBox.Teacher.entity.Teacher;
import com.mani.CollageBox.Teacher.entityImpl.TeacherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherService {


    // fields
    @Autowired
    private TeacherImpl teacherImpl;
    @Autowired
    private StudentService studentService;
    @Autowired
    private Teacher teacher;


    // -- methods --
    public Teacher getTeacher(int id) {
        try {
            return teacherImpl.getTeacher(id);
        }catch (Exception e){}
        return null;
    }

    public boolean checkTeacherAccount(int id,String password){
        Teacher teacher=null;
        try{
            teacher = teacherImpl.getTeacher(id);
            System.out.println(teacher);
            System.out.println(id+" "+password);
        }catch (Exception e){}
        if(teacher.getPassword()!=password){
            return true;
        }
        return false;
    }

    public String[] getAllStudentName(){
        List<Student> students = studentService.getAllStudent();
        String studentNames[] = new String[students.size()];
        int i=0;
        for(Student student:students){
            studentNames[i] = student.getStudentName().toString();
            i++;
        }
        return studentNames;
    }


    public void Delete(int id){
        teacherImpl.teacherDelete(id);
    }

    public void teacherAdd(Teacher teacher){
        teacherImpl.teacherAdd(teacher);
    }

    public int getId(){
        return teacher.getTId();
    }
}
