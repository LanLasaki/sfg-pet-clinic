package guru.springframework.controllers;

import guru.springframework.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetService vetService;

    @Autowired
    VetController(VetService vetService){
        this.vetService = vetService;
    }
    @RequestMapping({"/vets", "/vets/indext", "/vets/index.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";

    }
}
