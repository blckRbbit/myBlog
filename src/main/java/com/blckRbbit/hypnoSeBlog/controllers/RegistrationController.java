package com.blckRbbit.hypnoSeBlog.controllers;

import com.blckRbbit.hypnoSeBlog.models.Role;
import com.blckRbbit.hypnoSeBlog.models.User;
import com.blckRbbit.hypnoSeBlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration () {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser (User user, Map<String, Object> model) {
        User userFromDataBase = userRepository.findByUsername(user.getUsername());
        if (userFromDataBase != null) {
            model.put("message", "Пользователь с таким именем существует!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
