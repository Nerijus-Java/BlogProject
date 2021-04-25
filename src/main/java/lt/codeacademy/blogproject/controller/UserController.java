package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(@Qualifier("userServiceMpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String openCreateProductForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create")
    public String createProduct(User user) {
        userService.addUser(user);
        return "redirect:/user/create";
    }
}
