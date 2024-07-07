package com.yaredgidey.studentdatauiapp.controller;

import com.yaredgidey.studentdatauiapp.model.Student;
import com.yaredgidey.studentdatauiapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getStudents(Model model){
//        List<Student> students = studentService.getStudents();
//        model.addAttribute("students",students);
        return findPaginated(1,"Id" , "asc", model);
    }

    @GetMapping("/student")
    @ResponseBody
    public String  getStudentById(Integer id, Model model){
        model.addAttribute(studentService.getStudentById(id));
        return " ";
    }

    @RequestMapping(value = "/save",method = {RequestMethod.POST, RequestMethod.PUT})
    public String updateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateStudent(@RequestParam("Id") Integer Id, @ModelAttribute("student") Student student,HttpServletRequest request ) {
              Student st = new Student();
               if(studentService.getStudentById(Id).isPresent()){
                     st = studentService.getStudentById(Id).get();
                   st.setId(student.getId());
                   st.setName(student.getName());
                   st.setLastName(student.getLastName());
                   st.setMajor(student.getMajor());
                   st.setDegreeProgram(student.getDegreeProgram());
                   st.setUpdateOn(student.getUpdateOn());
                   st.setUpdatedBy(student.getUpdatedBy());
               }
               System.out.println("");
         studentService.updateStudent(st);

        return "redirect:/students";

    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String searchStudent(Model model ,@Param("keyword") String keyword){
        List<Student> students = studentService.listAll(keyword);
        model.addAttribute("students",students);
        model.addAttribute("keyword", keyword);
        return "search";
    }

    @PostMapping(value = "/addNew")
    public String addStudent(Student student){
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/delete/{Id}",method = {RequestMethod.DELETE, RequestMethod.PUT,RequestMethod.GET})
    public  String deleteStudent(@PathVariable(value = "Id") Integer id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo ,
                                @RequestParam(value = "sortField") String sortField,
                                @RequestParam(value = "sortDir") String sortDir,
                                Model model){
        int pageSize = 5;
        Page<Student> page = studentService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Student> studentList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir" ,sortDir.equals("asc") ? "desc" : "asc" );
        model.addAttribute("listOfStudents" , studentList);
        return "students";
    }



}
