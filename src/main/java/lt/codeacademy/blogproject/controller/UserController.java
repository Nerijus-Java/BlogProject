package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Role;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.BlogService;
import lt.codeacademy.blogproject.service.RoleService;
import lt.codeacademy.blogproject.service.UserServiceIMPL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceIMPL userServiceIMPL;
    private final BlogService blogService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public UserController(UserServiceIMPL userServiceIMPL, @Qualifier("blogServiceIMPL") BlogService blogService
            , RoleService roleService, PasswordEncoder encoder) {
        this.userServiceIMPL = userServiceIMPL;
        this.blogService = blogService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/create")
    public String openCreateProductForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create")
    public String createProduct(@Valid User user, BindingResult errors) {

        if (errors.hasErrors()) {
            return "register";
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        user.setRoles(roles);

        String encodedPass = encoder.encode(user.getPassword());

        user.setPassword(encodedPass);
        userServiceIMPL.saveUser(user);
        return "redirect:/signIn";
    }

    @GetMapping("/blogs")
    public String openUsersBlogs(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("title", "user.blogs");
        model.addAttribute("blog", blogService.getByUser(user));
        model.addAttribute("myBlogs", true);
        return "blog";
    }
}
