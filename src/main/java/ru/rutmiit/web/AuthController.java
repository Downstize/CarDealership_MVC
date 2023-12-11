package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.UserRegistrationDto;
import ru.rutmiit.models.User;
import ru.rutmiit.services.AuthService;
import ru.rutmiit.services.UserService;
import ru.rutmiit.views.UserProfileView;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    private  final UserService userService;
    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDto userRegistrationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        this.authService.register(userRegistrationDto);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String userName = principal.getName();
        User user = authService.getUser(userName);

        UserProfileView userProfileView = new UserProfileView(
                userName,
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }
}
