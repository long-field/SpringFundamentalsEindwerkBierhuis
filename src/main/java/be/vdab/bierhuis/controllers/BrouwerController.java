package be.vdab.bierhuis.controllers;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("brouwers")
public class BrouwerController {
    private final BrouwerService brouwerService;
    private final BierService bierService;
    public BrouwerController(BrouwerService brouwerService, BierService bierService) {
        this.brouwerService = brouwerService;
        this.bierService = bierService;
    }
    @GetMapping
    public ModelAndView brouwers() {
        var modelAndView = new ModelAndView("brouwers","brouwers", brouwerService.findAll());
        return modelAndView;
    }
    @GetMapping("{brouwerId}")
    public ModelAndView bierenVanBrouwer(@PathVariable long brouwerId) {
        var modelAndView =new ModelAndView("brouwer");
        modelAndView.addObject("bieren",bierService.findBierenFromBrouwer(brouwerId));
        brouwerService.findById(brouwerId).ifPresent(brouwer -> modelAndView.addObject("brouwer",brouwer));
        return modelAndView;
    }
    @GetMapping("/{brouwerId}/{id}")
    public ModelAndView bier( @PathVariable ("brouwerId") long brouwerId, @PathVariable ("id")long id) {
        var modelAndView =new ModelAndView("bier");
        bierService.findById(id).ifPresent(bier -> { modelAndView.addObject("bier", bier);});
        brouwerService.findById(brouwerId).ifPresent(brouwer -> modelAndView.addObject(brouwer));
        modelAndView.addObject("aantal");
        return modelAndView;
    }
    //seems to work fine
}
