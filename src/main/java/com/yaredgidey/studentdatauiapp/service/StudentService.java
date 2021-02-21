package com.yaredgidey.studentdatauiapp.service;

import com.yaredgidey.studentdatauiapp.model.Student;
import com.yaredgidey.studentdatauiapp.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents(){
       return  studentRepository.findAll();
    }
    public Optional<Student> getStudentById(Integer id){
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
    public List<Student>listAll(String keyword){
        if(keyword != null){
            return studentRepository.search(keyword);
        }
        return  studentRepository.findAll();
    }
    public Page<Student> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection){

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
        Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber -1,pageSize,sort);
        return this.studentRepository.findAll(pageable);
    }
}
