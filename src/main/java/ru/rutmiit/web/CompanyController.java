package ru.rutmiit.web;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rutmiit.dto.AddCompanyDto;
import ru.rutmiit.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String addCompany() {
        return "company-add";
    }

    @ModelAttribute("companyModel")
    public AddCompanyDto initCompany() {
        return new AddCompanyDto();
    }

    @PostMapping("/add")
    public String addCompany(@Valid AddCompanyDto companyModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("companyModel", companyModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyModel",
                    bindingResult);
            return "redirect:/companies/add";
        }
        companyService.addCompany(companyModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllCompanies(Model model) {
        model.addAttribute("companyInfos", companyService.allCompanies());

        return "company-all";
    }

    @GetMapping("/company-details/{company-name}")
    public String companyDetails(@PathVariable("company-name") String companyName, Model model) {
        model.addAttribute("companyDetails", companyService.companyDetails(companyName));

        return "company-details";
    }

    @GetMapping("/company-delete/{company-name}")
    public String deleteCompany(@PathVariable("company-name") String companyName) {
        companyService.removeCompany(companyName);

        return "redirect:/companies/all";
    }
}
