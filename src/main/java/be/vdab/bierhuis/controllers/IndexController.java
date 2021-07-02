package be.vdab.bierhuis.controllers;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private final BierService bierService;
    public IndexController(BierService bierService) {
        this.bierService = bierService;
    }
    @GetMapping
    public ModelAndView index() {
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("aantalBieren", bierService.findAantal());
        return modelAndView;
    }
}
