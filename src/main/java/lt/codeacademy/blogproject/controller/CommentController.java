package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Blog;
import lt.codeacademy.blogproject.model.Comment;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import lt.codeacademy.blogproject.service.CommentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;

    public CommentController(@Qualifier("blogServiceIMPL") BlogService blogService
            , @Qualifier("commentServiceIMPL") CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/create")
    public String openCreateComment(Model model, @RequestParam UUID id) {

        model.addAttribute("comment", new Comment());
        model.addAttribute("blogId", id);
        return "comments";
    }

    @PostMapping("/create/{id}")
    public String createComment(@Valid Comment comment, BindingResult errors, @AuthenticationPrincipal User user, @PathVariable UUID id) {

        if (errors.hasErrors()) {
            return "comments";
        }

        comment.setUser(user);
        comment.setBlog(blogService.getBlog(id));

        commentService.addComment(comment);

        return "redirect:/blog/open?id=" + id.toString().trim();
    }

    @GetMapping("/delete/{id}")
    private String deleteComment(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        User commentUser = commentService.getComment(id).getUser();
        Blog blog = commentService.getComment(id).getBlog();
        if (user.getUserID().equals(commentUser.getUserID()) || user.getUserID().equals(blog.getUser().getUserID())) {
            commentService.removeComment(id);
            return "redirect:/blog/open?id=" + blog.getBlogID();
        }
        return "redirect:/blog";
    }

    @GetMapping("/update")
    private String updateComment(@RequestParam UUID id, Model model){
        Comment comment = commentService.getComment(id);

        model.addAttribute("comment", comment);
        model.addAttribute("blogId", comment.getBlog().getBlogID());
        return "updateComment";
    }

    @PostMapping("/update/{id}")
    public String updateComment(Comment comment, @AuthenticationPrincipal User user, @PathVariable UUID id,@RequestParam UUID commentID){
        Blog blog = blogService.getBlog(id);

        comment.setCommentID(commentID);
        comment.setBlog(blog);
        comment.setUser(user);
        commentService.updateComment(comment);

        return "redirect:/blog/open?id=" + blog.getBlogID();
    }
}
