package com.mani.CollageBox.Teacher.entityImpl;

import com.mani.CollageBox.Teacher.entity.Teacher;
import com.mani.CollageBox.Teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherImpl {

    @Autowired
    private TeacherRepository repository;

    public Teacher getTeacher(int id){
        return repository.findById(id).orElse(null);
    }

    public void teacherDelete(int id){
        repository.deleteById(id);
    }

    public void teacherAdd(Teacher teacher){
        repository.save(teacher);
    }

}
