package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entites.Role;
import ru.kata.spring.boot_security.demo.entites.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;


import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;

    public UserController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user")
    public String user(ModelMap model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String home(ModelMap model) {
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(ModelMap model) {
        User user = new User();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "new";
    }

    @PostMapping("/admin/new")
    public String create(@ModelAttribute("user")  User user) {
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }


    @GetMapping("/admin/edit/{id}")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "edit";
    }

    @PatchMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user")  User user,
                         @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}