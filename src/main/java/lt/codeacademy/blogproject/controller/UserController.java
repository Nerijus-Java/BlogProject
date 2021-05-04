package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public/user")
public class UserController {

    @GetMapping("/create")
    public String openCreateProductForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create")
    public String createProduct(User user) {
        //TODO save user
        return "redirect:/public/user/create";
    }
}
