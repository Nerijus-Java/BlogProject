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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;
    private final RoleService roleService;
    private final UserServiceIMPL userServiceIMPL;

    public CommentController(@Qualifier("blogServiceIMPL") BlogService blogService
            , @Qualifier("commentServiceIMPL") CommentService commentService, RoleService roleService, UserServiceIMPL userServiceIMPL) {
        this.blogService = blogService;
        this.commentService = commentService;
        this.roleService = roleService;
        this.userServiceIMPL = userServiceIMPL;
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

        Role r = roleService.getRoleByName("ADMIN");

        if (user.getUserID().equals(commentUser.getUserID()) || user.getUserID().equals(blog.getUser().getUserID())) {
            commentService.removeComment(id);
            return "redirect:/blog/open?id=" + blog.getBlogID();

        } else if (user.getRoles().stream().anyMatch(role -> role.getId().equals(r.getId()))) {
            commentService.removeComment(id);
            return "redirect:/blog/open?id=" + blog.getBlogID();
        }

        return "redirect:/blog";
    }

    @GetMapping("/update")
    private String updateComment(@RequestParam UUID id, Model model) {
        Comment comment = commentService.getComment(id);

        model.addAttribute("comment", comment);
        model.addAttribute("blogId", comment.getBlog().getBlogID());
        return "updateComment";
    }

    @PostMapping("/update/{id}")
    public String updateComment(Comment comment, @AuthenticationPrincipal User loggedIn, @PathVariable UUID id
            , @RequestParam UUID commentID, @RequestParam UUID commentUserID) {

        Blog blog = blogService.getBlog(id);
        Role r = roleService.getRoleByName("ADMIN");

        comment.setCommentID(commentID);
        comment.setBlog(blog);
        comment.setUser(userServiceIMPL.getUserById(commentUserID));

        if (loggedIn.getUserID().equals(comment.getUser().getUserID())) {
            commentService.updateComment(comment);
            return "redirect:/blog/open?id=" + blog.getBlogID();
        } else if (loggedIn.getRoles().stream().anyMatch(role -> role.getId().equals(r.getId()))) {
            commentService.updateComment(comment);
        }
        return "redirect:/blog/open?id=" + blog.getBlogID();
    }
}
