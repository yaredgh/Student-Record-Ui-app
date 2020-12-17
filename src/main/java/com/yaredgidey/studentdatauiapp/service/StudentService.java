package com.yaredgidey.studentdatauiapp.service;

import com.yaredgidey.studentdatauiapp.model.Student;
import com.yaredgidey.studentdatauiapp.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
       return (List<Student>) studentRepository.findAll();
    }
    public Optional <Student> getStudentById(Integer id){
        return studentRepository.findById(id);
    }
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void updateStudent(Student student){
        studentRepository.save(student);
    }
    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }
}
