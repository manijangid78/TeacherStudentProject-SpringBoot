package com.mani.CollageBox.Student.controller;

import com.mani.CollageBox.Student.entity.Student;
import com.mani.CollageBox.Student.entity.StudentName;
import com.mani.CollageBox.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("id")
public class StudentController {

    // --- field ---
    @Autowired
    private StudentService studentService;

    // --- Mappings ---
    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("studentOperation")
    public String getOperation(){
        return "studentOperation";
    }

    // ---student get data---
    @GetMapping("studentGetData")
    public String getStudentData(ModelMap model,HttpSession session){
        Student student = studentService.getStudent((int)session.getAttribute("id"));
        model.addAttribute("studentData",student);
        System.out.println(student);
        return "studentGetData";
    }


    // --- student delete

    @GetMapping("teacherStudentOperation")
    public String getTeacherStudentOperation(){
        return "teacherStudentOperation";
    }

    @GetMapping("studentDelete")
    public String studentDelete(Model model, HttpSession session){
        boolean bool = studentService.studentDelete((int)session.getAttribute("id"));
        session.removeAttribute("id");
        return "studentLogIn";
    }

    // --edit get mapping
    @GetMapping("studentEdit")
    public String studentEdit(HttpSession session,ModelMap model){
        Student student = studentService.getStudent((int)session.getAttribute("id"));
        model.addAttribute("newStudent",student);
        return "studentEdit";
    }

    @GetMapping("studentCreate")
    public String studentCreate(ModelMap model){
        int std_id=studentService.getId();
        StudentName studentName = new StudentName("","","");
        Student student = new Student(std_id,"",studentName,"","","",6);
        model.addAttribute("newStudent",student);
        return "studentCreate";
    }


    // --check login id and password----
    @PostMapping("studentOperation")
    public String studentOperation(@RequestParam(value = "id",defaultValue = "-1")int id,
                                   @RequestParam(value = "password",defaultValue = "")String password,
                                   ModelMap model) {
        Student student = new Student();
        student.setId(id);
        student.setPassword(password);
        boolean bool=false;
        try{
            bool= studentService.checkLogIn(student);
        }catch (Exception e){}
        if(bool) {
            model.put("id",id);
            return "studentOperation";
        }
        model.addAttribute("logInFailedMessage","Incorrect Id/Password");
        return "studentLogIn";
    }

    @PostMapping("studentCreate")
    public String studentCreate(@RequestParam(value = "id",defaultValue ="-1")int id,
                                @RequestParam("firstName")String firstName,
                                @RequestParam("middleName")String middleName,
                                @RequestParam("lastName")String lastName,
                                @RequestParam("rollNo")String rollNo,
                                @RequestParam("branch")String branch,
                                @RequestParam("semester")int semester,
                                @RequestParam("mobileNo")String mobileNo,
                                @RequestParam("password")String password,
                                HttpSession session){
        StudentName studentName = new StudentName(firstName,middleName,lastName);
        Student student = new Student(id,rollNo,studentName,branch,mobileNo,password,semester);
        studentService.addStudent(student);
        if(session.getAttribute("id")!=null){
            return "studentOperation";
        }
        return "studentLogIn";
    }
}
