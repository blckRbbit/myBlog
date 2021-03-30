package com.blckRbbit.hypnoSeBlog.controllers;

import com.blckRbbit.hypnoSeBlog.models.Post;
import com.blckRbbit.hypnoSeBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Главная");
        Iterable <Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
}