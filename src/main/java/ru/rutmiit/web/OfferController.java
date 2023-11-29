package ru.rutmiit.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rutmiit.dto.AddOfferDto;
import ru.rutmiit.services.ModelService;
import ru.rutmiit.services.OfferService;
import ru.rutmiit.services.UserService;

@Controller
@RequestMapping("/offers")
public class OfferController {


    @Autowired
    private final OfferService offerService;
    @Autowired
    private final ModelService modelService;
    @Autowired
    private final UserService userService;
    public OfferController(OfferService offerService, ModelService modelService, UserService userService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.getAll());
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
        model.addAttribute("offerInfos", offerService.getAll());

        return "offer-all";
    }

    @GetMapping("/offer-details/{offer-name}")
    public String OfferDetails(@PathVariable("offer-name") int yearOfOffer, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(yearOfOffer));

        return "offer-details";
    }

    @GetMapping("/offer-delete/{offer-name}")
    public String deleteBrand(@PathVariable("brand-name") Double offerPrice) {
        offerService.removeOffer(offerPrice);

        return "redirect:/offer/all";
    }
}
