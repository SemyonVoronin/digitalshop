package school.voronin.digitalshop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.voronin.digitalshop.model.User;
import school.voronin.digitalshop.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model
    ){
        try {
            User user = userService.login(email, password);
            session.setAttribute("currentUser", user);
            return "redirect:/";
        }catch (IllegalArgumentException ex){
            model.addAttribute("error", ex.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("currentUser");
        return "redirect:/";
    }
}
