package com.mani.CollageBox.Teacher.controller;

import com.mani.CollageBox.Student.entity.Student;
import com.mani.CollageBox.Student.service.StudentService;
import com.mani.CollageBox.Teacher.entity.Teacher;
import com.mani.CollageBox.Teacher.entity.TeacherName;
import com.mani.CollageBox.Teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("id")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("teacherLogIn")
    public String teacherLogIn(){
        return "teacherLogIn";
    }

    @GetMapping("teacherOperation")
    public String teacherOperation(){
        return "teacherOperation";
    }

    @GetMapping("teacherGetData")
    public String teacherGetData(ModelMap model,HttpSession session){
        Teacher teacher = teacherService.getTeacher((int)session.getAttribute("id"));
        model.addAttribute("teacherData",teacher);
        return "teacherGetData";
    }

    @GetMapping("teacherDelete")
    public String teacherDelete(HttpSession session){
        teacherService.Delete((int)session.getAttribute("id"));
        return "teacherLogIn";
    }

    @GetMapping("teacherEdit")
    public String teacherEditData(ModelMap model,HttpSession session){
        Teacher teacher = teacherService.getTeacher((int)session.getAttribute("id"));
        model.addAttribute("teacherEditData",teacher);
        return "teacherEdit";
    }

    @GetMapping("teacherCreate")
    public  String teacherCreate(ModelMap model){
        int id = teacherService.getId();
        TeacherName teacherName = new TeacherName("","","");
        Teacher teacher = new Teacher(id,teacherName,"","","");
        model.addAttribute("newTeacher",teacher);
        return "teacherCreate";
    }

    @PostMapping("teacherOperation")
    public String teacherLogIn(@RequestParam("id")int id, @RequestParam("password")String password, ModelMap model){
        boolean bool = false;
        try{
            bool = teacherService.checkTeacherAccount(id,password);
        }catch (Exception e){}
        System.out.println(bool);
        if(bool){
            model.put("id",id);
            return "teacherOperation";
        }
        model.addAttribute("teacherLogInFailedMessage","Incorrect Id/Password");
        return "teacherLogIn";
    }

    @PostMapping("teacherCreate")
    public String teacherCreate(@RequestParam("id")int id,
                                @RequestParam(value = "firstName",defaultValue ="")String firstName,
                                @RequestParam(value = "middleName",defaultValue = "")String middleName,
                                @RequestParam(value = "lastName",defaultValue = "")String lastName,
                                @RequestParam(value = "subject",defaultValue = "")String subject,
                                @RequestParam(value = "password",defaultValue = "")String password,
                                @RequestParam(value = "mobileNo",defaultValue = "")String mobileNo){
        TeacherName teacherName = new TeacherName(firstName,middleName,lastName);
        Teacher teacher = new Teacher(id,teacherName,subject,password,mobileNo);
        teacherService.teacherAdd(teacher);
        return "teacherLogIn";
    }


    // teacher get students data

    @GetMapping("teacherStudentData")
    public String teacherStudentData(){
        return "teacherStudentData";
    }

    @GetMapping("teacherStudentDelete")
    public String teacherStudentDelete(){
        return "teacherStudentDelete";
    }

    @GetMapping("teacherStudentEdit")
    public String teacherStudentEdit() {
        return "teacherStudentEdit";
    }

    @PostMapping("teacherStudentData")
    public String teacherStudentData(@RequestParam("id")int id,ModelMap model){
        Student student = studentService.getStudent(id);
        if(student==null){
            model.addAttribute("studentNotFoundMessage","Student Not Found");
        }else {
            model.addAttribute("studentData", student);
        }
        return "teacherStudentData";
    }

    @PostMapping("teacherStudentDelete")
    public String teacherStudentDelete(@RequestParam("id")int id,ModelMap model){
        studentService.studentDelete(id);
        model.addAttribute("studentDeleteMessage","Student Data Deleted");
        return "teacherStudentDelete";
    }

}
