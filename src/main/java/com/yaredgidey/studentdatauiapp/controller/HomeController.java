package com.yaredgidey.studentdatauiapp.controller;

import com.yaredgidey.studentdatauiapp.model.Student;
import com.yaredgidey.studentdatauiapp.model.User;
import com.yaredgidey.studentdatauiapp.repo.UserRepo;
import com.yaredgidey.studentdatauiapp.service.UserService;
import com.yaredgidey.studentdatauiapp.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserServiceImp userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public HomeController(UserServiceImp userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public HomeController() {
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public String goHome() {
        return "home";
    }
    @GetMapping("/signUp")
    public  String signUp(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String saveAccount(@Valid User user, BindingResult result, Model model) throws Exception {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "signUp";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailExists", true);

            return "signUp";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("username Exists", true);

            return "signUp";
        }
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return "login";
    }
    @RequestMapping("/login")
    public String loginPage(){
        return  "login";
    }
    @RequestMapping(value = "/creatAdmin",method = {RequestMethod.POST, RequestMethod.PUT,RequestMethod.GET})
    public String creatAdmin( Model model, User user){
        model.addAttribute("user", user);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.createAdmin(user);

        return "redirect:/home";
    }


}
