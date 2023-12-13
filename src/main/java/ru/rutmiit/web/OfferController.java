package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddOfferDto;
import ru.rutmiit.dto.ShowModelInfoDto;
import ru.rutmiit.dto.ShowOfferInfoDto;
import ru.rutmiit.services.BrandService;
import ru.rutmiit.services.ModelService;
import ru.rutmiit.services.OfferService;
import ru.rutmiit.services.UserService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/offers")
public class OfferController {


    @Autowired
    private final OfferService offerService;
    @Autowired
    private final ModelService modelService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BrandService brandService;
    public OfferController(OfferService offerService, ModelService modelService, UserService userService, BrandService brandService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAll());
        return "offer-add";}

    @ModelAttribute("offerModel")
    public AddOfferDto initModel() {return new AddOfferDto();}

    @PostMapping("/offer/create")
    public String createOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offers/add";
        }
        offerService.register(offerModel);

        return "redirect:/";
    }

    @GetMapping("/offer/all")
    public String showAllOffers(Model model) {

        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("offerInfos", offerService.getAll());

        return "offer-all";
    }

    @GetMapping("/offer-details/{offer-id}")
    public String OfferDetails(@PathVariable("offer-id") String id, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(id));

        return "offer-details";
    }

    @GetMapping("/offer/sortByPrice")
    public String sortByPrice(Model model) {
        List<ShowOfferInfoDto> offerInfos = offerService.getAll();
        offerInfos.sort(Comparator.comparingDouble(ShowOfferInfoDto::getPrice));
        model.addAttribute("offerInfos", offerInfos);
        return "offer-all";
    }

    // New controller method to handle sorting by year
    @GetMapping("/offer/sortByYear")
    public String sortByYear(Model model) {
        List<ShowOfferInfoDto> offerInfos = offerService.getAll();
        offerInfos.sort(Comparator.comparingInt(ShowOfferInfoDto::getYear));
        model.addAttribute("offerInfos", offerInfos);
        return "offer-all";
    }

    @GetMapping("/offer-by-model")
    public String showOffersByModel(@RequestParam(name = "model", required = false) String modelName, Model model) {
        if ("all".equals(modelName)) {
            model.addAttribute("offerInfos", offerService.getAll());
        } else if (modelName != null && !modelName.isEmpty()) {
            List<ShowOfferInfoDto> offersByModel = offerService.getOffersByModel(modelName);
            model.addAttribute("offerInfos", offersByModel);
        } else {
            model.addAttribute("offerInfos", offerService.getAll());
        }

        // Add availableModels to the model for repopulating the dropdown
        model.addAttribute("availableModels", modelService.getAllModels());

        return "offer-all";
    }





    @GetMapping("/offer-delete/{offer-id}")
    public String deleteOffer(@PathVariable("offer-id") String id) {
        offerService.removeOffer(id);

        return "redirect:/";
    }

    @GetMapping("/offer-edit/{offer-id}")
    public String editOfferForm(@PathVariable("offer-id") String id, Model model) {

        Optional<AddOfferDto> offerDto = offerService.findOffer(id);
        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAll());

        model.addAttribute("offer", offerDto);
        return "model-edit";
    }
    @PostMapping("/offer-edit/{offer-id}")
    public String editOffer(@PathVariable("offer-id") String id, @Valid AddOfferDto offerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAll());

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offer", offerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:/offers/offer-edit";
        }

        offerService.editOffer(id, offerDto);
        return "redirect:/";
    }
}
