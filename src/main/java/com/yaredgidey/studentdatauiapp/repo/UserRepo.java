package com.yaredgidey.studentdatauiapp.repo;

import com.yaredgidey.studentdatauiapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

     User findByUsername(String username);
     User findByName(String name);
     User findByEmail(String email);

}
