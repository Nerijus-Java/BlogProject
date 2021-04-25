package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import lt.codeacademy.blogproject.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;
    private final UserService userService;
    public BlogController(@Qualifier("blogServiceMpl") BlogService blogService,@Qualifier("userServiceMpl") UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping
    public String getBlogs(Model model){
        model.addAttribute("blog",blogService.getAllBlogs());
        return "blog";
    }

    @GetMapping("/open")
    public String openBlog(@RequestParam UUID id, Model model){
        Blog blog = blogService.getBlog(id);
        User user = userService.getUser(blog.getUserID());

        if (user != null){
            model.addAttribute("user",user);
        }
        model.addAttribute("blog",blog);
        return "oneBlog";
    }
}
