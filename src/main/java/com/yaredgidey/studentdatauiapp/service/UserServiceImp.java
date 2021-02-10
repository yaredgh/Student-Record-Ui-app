package com.yaredgidey.studentdatauiapp.service;

import com.yaredgidey.studentdatauiapp.model.ERole;
import com.yaredgidey.studentdatauiapp.model.Role;
import com.yaredgidey.studentdatauiapp.model.User;
import com.yaredgidey.studentdatauiapp.repo.UserRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepo;

    public UserServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserServiceImp() {
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void save(User user) {
        User localUser = userRepo.findByUsername(user.getUsername());
        if (localUser != null) {
            LOGGER.info("user {} already exists. Nothing will be done.", user.getUsername());
        }
        else {
            Set<Role> userRoles = new HashSet<>();
            Role role0 = new Role();
            role0.setName(ERole.ROLE_USER);
            userRoles.add(role0);
            user.setRoles(userRoles);
            userRepo.save(user);
        }
    }

    @Override
    public Optional<User> findOne(long id) {
        return userRepo.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }


    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User createAdmin(User admin) {
        User localUser = userRepo.findByUsername(admin.getUsername());

        if (localUser != null) {
            LOGGER.info("user {} already exists. Nothing will be done.", admin.getUsername());
        }else {
            Set<Role> adminRoles = new HashSet<>();
            Role role1 = new Role();
            role1.setName(ERole.ROLE_ADMIN);
            adminRoles.add(role1);
            admin.setRoles(adminRoles);
            localUser = userRepo.save(admin);
        }
        return localUser;
    }
}