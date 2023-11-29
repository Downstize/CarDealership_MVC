package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.dtooo.AddUserRoleDto;
import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.services.serv.UserRoleService;

@RestController
public class UserRoleController {

    @Autowired
    private final UserRoleService userRoleService;
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/add")
    public String addUserRole() {return "user-role-add";}

    @ModelAttribute("userRoleModel")
    public AddUserRoleDto initUserRole() {return new AddUserRoleDto();}

    @PostMapping("/userRole/create")
    public String createUserRole(@Valid AddUserRoleDto userRoleModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRoleModel", userRoleModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRoleModel", bindingResult);
            return "redirect:/user/add";
        }
        userRoleService.addRole(userRoleModel);

        return "redirect:/";
    }

    @GetMapping("/userRole/all")
    public String showAllUserRoles(Model model) {
        model.addAttribute("userRoleInfos", userRoleService.getAll());

        return "userRole-all";
    }

//    @GetMapping("/userRole-delete/{user-role}")
//    public String deleteUserRole(@PathVariable("user-role") RoleEnum roleEnum) {
//        userRoleService.removeUserRole(roleEnum);
//
//        return "redirect:/useRole/all";
//    }
}
