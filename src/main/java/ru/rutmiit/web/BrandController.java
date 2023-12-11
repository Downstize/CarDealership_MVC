package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddBrandDto;
import ru.rutmiit.services.BrandService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private static final Logger LOG = Logger.getLogger(String.valueOf(Controller.class));


    @Autowired
    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {return "brand-add";}

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {return new AddBrandDto();}

    @PostMapping("/brand/create")
    public String createBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel", bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/";
    }


    @GetMapping("/brand/all")
    public String showAllBrands(Model model, Principal principal) {
        LOG.log(Level.INFO, "Show all Brands for " + principal.getName());
        model.addAttribute("brandInfos", brandService.getAll());

        return "brand-all";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, Model model) {
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }


    @GetMapping("/brand-delete/{brand-name}")
    public String deleteBrand(@PathVariable("brand-name") String brandName) {
        brandService.removeBrand(brandName);

        return "redirect:/";
    }

    @GetMapping("/brand-edit/{brand-name}")
    public String editBrandForm(@PathVariable("brand-name") String brandName, Model model) {

        AddBrandDto brand = brandService.findBrandByName(brandName);

        model.addAttribute("brand", brand);
        return "brand-edit";
    }
    @PostMapping("/brand-edit/{brand-name}")
    public String editBrand(@PathVariable("brand-name") String brandName, @Valid AddBrandDto brandDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("brand", brandDto);
            return "brand-edit";
        }

        brandService.editBrand(brandName, brandDto);
        return "redirect:/";
    }

}
