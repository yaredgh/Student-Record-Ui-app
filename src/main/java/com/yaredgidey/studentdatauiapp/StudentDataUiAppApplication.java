package com.yaredgidey.studentdatauiapp;

import com.yaredgidey.studentdatauiapp.model.ERole;
import com.yaredgidey.studentdatauiapp.model.Role;
import com.yaredgidey.studentdatauiapp.model.User;
import com.yaredgidey.studentdatauiapp.repo.UserRepo;
import com.yaredgidey.studentdatauiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class StudentDataUiAppApplication implements CommandLineRunner  {
    private static final String SALT = "salt";
    @Autowired
    UserService userService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentDataUiAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder().encode("admin"));
        admin.setEmail("admin@admin.com");
        admin.setName("Admin");
        admin.setLastName("Admin");
        Set<Role> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setName(ERole.ROLE_ADMIN);
        userRoles.add(role1);
        admin.setRoles(userRoles);

        userService.createUser(admin);

    }
}
