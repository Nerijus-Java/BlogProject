package lt.codeacademy.blogproject.controller;

import lt.codeacademy.blogproject.model.Role;
import lt.codeacademy.blogproject.model.User;
import lt.codeacademy.blogproject.service.RoleService;
import lt.codeacademy.blogproject.service.UserServiceMpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceMpl userServiceMpl;
    private final RoleService roleService;

    public UserController(UserServiceMpl userServiceMpl, RoleService roleService) {
        this.userServiceMpl = userServiceMpl;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String openCreateProductForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create")
    public String createProduct(User user) {
        Set<Role> roles = new HashSet<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        roles.add(roleService.getRoleByName("USER"));
        user.setRoles(roles);

        String encodedPass = encoder.encode(user.getPassword());

        user.setPassword("{bcrypt}" + encodedPass);
        userServiceMpl.saveUser(user);
        return "redirect:/signIn";
    }
}
