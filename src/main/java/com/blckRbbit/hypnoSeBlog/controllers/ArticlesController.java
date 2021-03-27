package com.blckRbbit.hypnoSeBlog.controllers;

import com.blckRbbit.hypnoSeBlog.models.Post;
import com.blckRbbit.hypnoSeBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ArticlesController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/articles")
    public String articlesPage(Model model) {
        Iterable <Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "articles";
    }

    @GetMapping("/articles/add")
    public String addArticles(Model model) {
        return "add_articles";
    }

    @PostMapping("/articles/add")
    public String getNewPost(@RequestParam String title,
                             @RequestParam String announce,
                             @RequestParam String content, Model model) {
        Post post = new Post(title, announce, content);
        postRepository.save(post);
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String detailArticle(@PathVariable(value = "id") long postId, Model model) {

        if (!postRepository.existsById(postId)) return "redirect:/articles";
        Optional<Post> post = postRepository.findById(postId);
        ArrayList <Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "detail_article";

    }

    @GetMapping("/articles/{id}/edit")
    public String editArticle(@PathVariable(value = "id") long postId, Model model) {

        if (!postRepository.existsById(postId)) return "redirect:/articles";
        Optional<Post> post = postRepository.findById(postId);
        ArrayList <Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("post", result);
        return "edit_article";

    }

    @PostMapping("/articles/{id}/edit")
    public String updatePost(@PathVariable(value = "id") long postId,
                             @RequestParam String title,
                             @RequestParam String announce,
                             @RequestParam String content, Model model) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setTitle(title);
        post.setAnnounce(announce);
        post.setContent(content);
        postRepository.save(post);
        return "redirect:/articles";
    }


    @PostMapping("/articles/{id}/remove")
    public String deletePost(@PathVariable(value = "id") long postId, Model model) {
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
        return "redirect:/articles";
    }
}
