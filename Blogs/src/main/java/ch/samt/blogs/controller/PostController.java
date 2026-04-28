package ch.samt.blogs.controller;

import ch.samt.blogs.models.Post;
import ch.samt.blogs.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // LISTA POST
    @GetMapping
    public String loadPosts(Model model) {
        model.addAttribute("posts", postService.getAllPost());
        return "postsList";
    }

    // Nuovo Post
    @GetMapping("/new")
    public String home(Model model) {
        model.addAttribute("post", new Post());
        return "home";
    }

    @PostMapping("/new")
    public String savePost(@Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return "home";
        }
        postService.addPost(post);
        return "redirect:/blog";
    }

    // DETTAGLIO POST
    @GetMapping("/post")
    public String getPost(@RequestParam(value = "postid", required = false) Long postid, Model model) {
        if(postid == null){
            model.addAttribute("posts", postService.getAllPost());
        }else {
            Post post = postService.findById(postid).orElseThrow();
            model.addAttribute("post", post);
            return "postDetail";
        }
        return "postsList";
    }
//    @GetMapping("/post")
//    public String getPost(Model model, @RequestParam(value = "postId", required = false) Long postId) {
//        if(postId == null) {
//            model.addAttribute("posts", postService.getAllPost());
//        } else {
//            postService.findById(postId);
//            return "redirect:/posts";
//        }
//
//        return "postsList";
//    }

    // FILTRO AUTORE
    @GetMapping("/{author}")
    public String loadInsertPage(Model model, @PathVariable String author) {
        if(author.isBlank()) {
            model.addAttribute("posts", postService.getAllPost());
        } else {
            List<Post> filteredPost = postService.getPostByAuthor(author);
            model.addAttribute("posts", filteredPost);
        }

        return "postsList";
    }

    // BEST POSTS
    @GetMapping("/best")
    public String bestPosts(Model model) {
        model.addAttribute("posts", postService.getBestPosts());
        return "postsList";
    }

    // LIKE
    @GetMapping("/like")
    public String addLike(@RequestParam Long postid) {
        postService.addLike(postid);
        return "redirect:/blog";
    }
}
