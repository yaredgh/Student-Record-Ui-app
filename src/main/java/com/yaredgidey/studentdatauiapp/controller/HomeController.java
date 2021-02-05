package com.yaredgidey.studentdatauiapp.controller;

import com.yaredgidey.studentdatauiapp.model.User;
import com.yaredgidey.studentdatauiapp.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
public class HomeController {


    private UserRepo userRepository;

   // private PasswordEncoder passwordEncoder;

    public HomeController(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public HomeController() {
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public String goHome() {
        return "home";
    }

    @PostMapping("/signUp")
    public String saveAccount(@Valid User user, BindingResult result, Model model) throws Exception {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "login";
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailExists", true);

            return "login";
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("emailExists", true);

            return "login";
        }
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "login";
    }
    @RequestMapping("/login")
    public String loginPage(){
        return  "login";
    }

}
