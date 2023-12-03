package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddBrandDto;
import ru.rutmiit.dto.AddModelDto;
import ru.rutmiit.services.BrandService;
import ru.rutmiit.services.ModelService;

@Controller
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private final ModelService modelService;

    @Autowired
    private final BrandService brandService;
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("availableBrands", brandService.getAll());

        return "model-add";}

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {return new AddModelDto();}

    @PostMapping("/model/create")
    public String createModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel", bindingResult);
            return "redirect:/models/add";
        }
        modelService.addModel(modelModel);

        return "redirect:/";
    }

    @GetMapping("/model/all")
    public String showAllModels(Model model) {
        model.addAttribute("modelInfos", modelService.getAllModels());
        model.addAttribute("brands", brandService.getAll()); // Add this line

        return "model-all";
    }


    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, Model model) {
        model.addAttribute("modelDetails", modelService.modelDetails(modelName));

        return "model-details";
    }

    @GetMapping("/model-delete/{model-name}")
    public String deleteModel(@PathVariable("model-name") String modelName) {
        modelService.removeModel(modelName);

        return "redirect:/";
    }

}
