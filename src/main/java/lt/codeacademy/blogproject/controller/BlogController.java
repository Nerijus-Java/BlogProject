package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.Comment;
import lt.codeacademy.blogproject.model.Role;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import lt.codeacademy.blogproject.service.CommentService;
import lt.codeacademy.blogproject.service.RoleService;
import lt.codeacademy.blogproject.service.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;
    private final RoleService roleService;
    private final UserServiceIMPL userServiceIMPL;
    private final CommentService commentService;

    public BlogController(@Qualifier("blogServiceIMPL") BlogService blogService, RoleService roleService
            , UserServiceIMPL userServiceIMPL, @Qualifier("commentServiceIMPL") CommentService commentService) {
        this.blogService = blogService;
        this.roleService = roleService;
        this.userServiceIMPL = userServiceIMPL;
        this.commentService = commentService;
    }

    @GetMapping
    public String getBlogs(Model model) {
        model.addAttribute("blog", blogService.getAllBlogs());
        model.addAttribute("title", "Blogs");
        return "blog";
    }

    @GetMapping("/private/create")
    public String openCreateBlogForm(Model model,@AuthenticationPrincipal User user) {
        Role r = roleService.getRoleByName("ADMIN");

        if (user.getRoles().stream().anyMatch(role -> role.getId().equals(r.getId()))) {
            model.addAttribute("blog", new Blog());
            return "createBlog";
        }

        return "redirect:/blog";
    }

    @PostMapping("/private/create")
    public String createBlog(@Valid Blog blog, BindingResult errors, @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "createBlog";
        }

        blog.setUser(user);
        blogService.addBlog(blog);

        return "redirect:/blog";
    }

    @GetMapping("/open")
    public String openBlog(@RequestParam UUID id, Model model, @AuthenticationPrincipal User loggedInUser) {

        Blog blog = blogService.getBlog(id);
        User user = blog.getUser();
        Set<Comment> comments = blog.getComments();

        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }

        if (user != null) {
            model.addAttribute("user", user);
        }
        if (comments != null) {
            model.addAttribute("comments", comments);
        }

        model.addAttribute("blog", blog);
        return "oneBlog";
    }

    @GetMapping("/delete")
    public String deleteBlog(@RequestParam UUID id, @AuthenticationPrincipal User user) {
        Role r = roleService.getRoleByName("ADMIN");

        if (user.getRoles().stream().anyMatch(role -> role.getId().equals(r.getId()))) {
            blogService.removeBlog(id);
        }
        return "redirect:/blog";
    }

    @GetMapping("/update")
    private String updateBlog(@RequestParam UUID id, Model model) {
        Blog blog = blogService.getBlog(id);

        model.addAttribute("blog", blog);
        return "updateBlog";
    }

    @PostMapping("/update/{id}")
    private String updateBlog(Blog blog, @RequestParam UUID userID, @RequestParam UUID blogID,@AuthenticationPrincipal User user) {
        Set<Comment> comments = commentService.getCommentsByBlogID(blogID);
        Role r = roleService.getRoleByName("ADMIN");

        if (user.getRoles().stream().anyMatch(role -> role.getId().equals(r.getId()))) {
            blog.setBlogID(blogID);
            blog.setComments(comments);
            blog.setUser(userServiceIMPL.getUserById(userID));
            blogService.updateBlog(blog);

        }

        return "redirect:/blog";
    }
}
