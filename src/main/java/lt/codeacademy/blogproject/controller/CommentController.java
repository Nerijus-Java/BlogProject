package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Comment;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import lt.codeacademy.blogproject.service.CommentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;

    public CommentController(@Qualifier("blogServiceMpl") BlogService blogService
            , @Qualifier("commentServiceMpl") CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/create")
    public String openCreateComment(Model model, @RequestParam UUID id){

        model.addAttribute("comment",new Comment());
        model.addAttribute("blogId",id);
        return "comments";
    }

    @PostMapping("/create/{id}")
    public String createBlog(Comment comment, @AuthenticationPrincipal User user,@PathVariable UUID id) {
        comment.setUser(user);
        comment.setBlog(blogService.getBlog(id));

        commentService.addComment(comment);

        return "redirect:/blog/open?id=" + id.toString().trim();
    }
}