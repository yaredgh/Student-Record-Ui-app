package com.yaredgidey.studentdatauiapp.service;

import com.yaredgidey.studentdatauiapp.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public interface UserService {
    void deleteById(long id);

    void save(User user);

    Optional<User> findOne(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User createAdmin(User user) ;
}
