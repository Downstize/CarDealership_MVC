package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddUserDto;
import ru.rutmiit.services.UserRoleService;
import ru.rutmiit.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private final UserService userService;
    @Autowired
    private final UserRoleService userRoleService;
    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("availableRoles", userRoleService.getAll());
        return "user-add";}

    @ModelAttribute("userModel")
    public AddUserDto initUser() {return new AddUserDto();}

    @PostMapping("/user/create")
    public String createUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/add";
        }
        userService.addUser(userModel);

        return "redirect:/";
    }

    @GetMapping("/user/all")
    public String showAllUsers(Model model) {
        model.addAttribute("userInfos", userService.getAll());

        return "user-all";
    }

    @GetMapping("/user-details/{user-name}")
    public String UserDetails(@PathVariable("user-name") String userName, Model model) {
        model.addAttribute("userDetails", userService.userDetails(userName));

        return "user-details";
    }

    @GetMapping("/user-delete/{user-name}")
    public String deleteUser(@PathVariable("user-name") String userName) {
        userService.removeUser(userName);

        return "redirect:/";
    }
}
