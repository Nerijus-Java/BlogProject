package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.Comment;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(@Qualifier("blogServiceMpl") BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String getBlogs(Model model){
        model.addAttribute("blog",blogService.getAllBlogs());
        return "blog";
    }

    @GetMapping("/private/create")
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "createBlog";
    }

    @PostMapping("/private/create")
    public String createBlog(Blog blog,@AuthenticationPrincipal User user) {
        blog.setUser(user);
        blogService.addBlog(blog);

        return "redirect:/blog/private/create";
    }

    @GetMapping("/open")
    public String openBlog(@RequestParam UUID id, Model model){
        Blog blog = blogService.getBlog(id);

        User user = blog.getUser();
        Set<Comment> comments = blog.getComments();

        if (user != null){
            model.addAttribute("user",user);
        }
        if (comments != null){
            model.addAttribute("comments",comments);
        }

        model.addAttribute("blog",blog);
        return "oneBlog";
    }
}
