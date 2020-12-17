package com.yaredgidey.studentdatauiapp.repo;

import com.yaredgidey.studentdatauiapp.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

}
