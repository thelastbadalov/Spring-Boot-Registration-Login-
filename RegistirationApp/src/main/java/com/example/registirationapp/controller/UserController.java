package com.example.registirationapp.controller;

import com.example.registirationapp.entity.User;
import com.example.registirationapp.service.EmailChecking;
import com.example.registirationapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    EmailChecking emailChecking;

    @GetMapping("/register")
    public String registirationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String register(User user) {
        Optional<User> userOptional=emailChecking.chcekEmail(user);
        if(userOptional.isPresent()){
            return "redirect:register?eyni";
        }
        userService.save(user);
        return "redirect:register?success";
    }

    @GetMapping("/login")
    public String loginpage() {

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String email, @RequestParam String password, HttpServletRequest httpServletRequest) {
        Optional<User> userOptional = userService.login(email, password);
        if (!userOptional.isPresent()) {
            model.addAttribute("error", "istifadeci duzgun deyil");

            return "login";

        }
        httpServletRequest.getSession().setAttribute("user",userOptional.get());
        return "welcome";
    }
@GetMapping("/welcome")
    public String welcome(HttpServletRequest httpServletRequest){
       User u= (User) httpServletRequest.getSession().getAttribute("user");
        if(u==null){
            return "login";
        }
        return "welcome";

}
@GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,Model model){
        httpServletRequest.getSession().invalidate();
        model.addAttribute("logout","ugurla cixish etdiniz");
        return "login";
}
    }