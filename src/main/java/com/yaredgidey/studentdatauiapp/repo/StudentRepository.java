package com.yaredgidey.studentdatauiapp.repo;

import com.yaredgidey.studentdatauiapp.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
      @Query("SELECT s FROM Student s WHERE CONCAT(s.id, ' ' ,s.name, ' ',s.lastName, ' ', s.major, ' ', s.degreeProgram, ' ', s.updateOn, ' ', s.updatedBy) LIKE %?1%")

      List<Student>search(String keyword);

}
