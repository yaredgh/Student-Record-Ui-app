package com.yaredgidey.studentdatauiapp.repo;

import com.yaredgidey.studentdatauiapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
      List<Student>findAll();
}
