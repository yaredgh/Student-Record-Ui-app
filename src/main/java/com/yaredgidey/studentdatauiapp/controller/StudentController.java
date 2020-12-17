package com.yaredgidey.studentdatauiapp.controller;

import com.yaredgidey.studentdatauiapp.model.Student;
import com.yaredgidey.studentdatauiapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students",studentService.getStudents());
        return "students";
    }

    @GetMapping("/student")
    @ResponseBody
    public Optional<Student> getStudentById(Integer id, Model model){

        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "/save",method = {RequestMethod.POST, RequestMethod.PUT})
    public String updateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }
    @RequestMapping(value = "/addNew",method = {RequestMethod.POST, RequestMethod.PUT,RequestMethod.GET})
    public String addStudent(Student student){
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/delete",method = {RequestMethod.DELETE, RequestMethod.PUT,RequestMethod.GET})
    public  String deleteStudent(Integer id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

}
