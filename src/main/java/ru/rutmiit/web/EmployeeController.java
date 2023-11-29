package ru.rutmiit.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rutmiit.dto.AddEmployeeDto;
import ru.rutmiit.services.CompanyService;
import ru.rutmiit.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("availableCompanies", companyService.allCompanies());

        return "employee-add";
    }

    @ModelAttribute("employeeModel")
    public AddEmployeeDto initEmployee() {
        return new AddEmployeeDto();
    }

    @PostMapping("/add")
    public String addEmployee(@Valid AddEmployeeDto employeeModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeModel", employeeModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeModel",
                    bindingResult);
            return "redirect:/employees/add";
        }
        employeeService.addEmployee(employeeModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllEmployees(Model model) {
        model.addAttribute("allEmployees", employeeService.allEmployees());

        return "employee-all";
    }

    @GetMapping("/employee-details{employee-full-name}")
    public String showEmployeeDetails(@PathVariable("employee-full-name") String employeeFullName, Model model) {
        model.addAttribute("employeeDetails", employeeService.employeeInfo(employeeFullName));

        return "employee-details";
    }

    @GetMapping("/employee-delete{employee-full-name}")
    public String deleteEmployee(@PathVariable("employee-full-name") String employeeFullName) {
        employeeService.fireEmployee(employeeFullName);

        return "redirect:/employees/all";
    }
}
