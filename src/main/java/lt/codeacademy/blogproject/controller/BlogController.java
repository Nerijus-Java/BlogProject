package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.service.BlogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
