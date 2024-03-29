package com.maxi.ecommerce.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxi.ecommerce.enums.UserType;
import com.maxi.ecommerce.models.User;
import com.maxi.ecommerce.services.user.UserServiceImplementation;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public String home(HttpSession session, Model model) {
        // User user =
        // userService.findById(Long.parseLong(session.getAttribute("activeUser").toString())).get();
        model.addAttribute("activeuserid", session.getAttribute("activeuserid"));
        // model.addAttribute("user", user);
        return "user/home";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "/user/login";
    }

    @PostMapping("/auth")
    public String auth(User userLog, HttpSession session) {
        Optional<User> user = userService.findByEmail(userLog.getEmail());
        if (user.isPresent()) {
            session.setAttribute("activeuserid", user.get().getId());
            if (user.get().getUserType().equals(UserType.ADMIN)) {
                return "redirect:/administrator";
            } else {
                return "redirect:/";
            }
        } else {
            logger.info("User no exiate");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("activeuserid");
        return "redirect:/";
    }
}
