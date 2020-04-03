package ru.javamentor.preproject.controller;

import org.springframework.web.bind.annotation.*;
import ru.javamentor.preproject.model.Role;
import ru.javamentor.preproject.model.User;
import ru.javamentor.preproject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private UserService serv;

    public UserController(UserService serv) {
        this.serv = serv;
    }

    @GetMapping("/user")
    public ModelAndView home(Authentication auth, ModelAndView mav) {
        mav.addObject("user", serv.getUserByName(auth.getName()));
        mav.setViewName("user-info");
        return mav;
    }

    @GetMapping("/admin")
    public ModelAndView allUsers(ModelAndView mav) {
        List<User> allUser = serv.getAllUsers();
        mav.setViewName("user-main");
        mav.addObject("users", allUser);
        return mav;
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, HttpServletRequest req) {
        Set<Role> roles = user.getRoles();
        String RoleUser = req.getParameter("role1");
        String RoleAdmin = req.getParameter("role2");
        if (RoleUser != null) {
            roles.add(Role.USER);
        }
        if (RoleAdmin != null) {
            roles.add(Role.ADMIN);
        }
        user.setRoles(roles);
        serv.addUser(user);
        return "redirect: /admin";
    }

    @PostMapping("/admin/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", serv.getUserById(id));
        return "user-edit";
    }

    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, HttpServletRequest req) {
        Set<Role> roles = user.getRoles();
        String RoleUser = req.getParameter("role1");
        String RoleAdmin = req.getParameter("role2");
        if (RoleUser != null) {
            roles.add(Role.USER);
        }
        if (RoleAdmin != null) {
            roles.add(Role.ADMIN);
        }
        user.setRoles(roles);
        serv.updateUser(user);
        return "redirect: /admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") int id) {
        serv.deleteUser(serv.getUserById(id));
        return "redirect: /admin";
    }
}
