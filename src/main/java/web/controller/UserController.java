package web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user/getAllUsers";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("getUser", userService.getUser(id));
        return "user/getUser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new";
        } else {
            userService.saveUser(user);
            return "redirect:/";
        }
    }

    @GetMapping("/update/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "user/updateUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/updateUser";
        } else {
            userService.updateUser(user);
            return "redirect:/";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
