package be.vdab.bierhuis.controllers;
import be.vdab.bierhuis.domain.*;
import be.vdab.bierhuis.services.BestelbonService;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;
    private final BestelbonService bestelbonService;
    public MandjeController(Mandje mandje, BierService bierService, BestelbonService bestelbonService) {
        this.mandje = mandje;
        this.bierService = bierService;
        this.bestelbonService = bestelbonService;
    }
    @PostMapping("/{id}")
    public String voegToe(@ModelAttribute("aantal") @Valid @Positive Long aantal, @PathVariable Long id) {
        mandje.voegToe(id, aantal);
        return "redirect:/mandje";
    }
    @GetMapping
    public ModelAndView toonMandje() {
        TreeSet<BestelbonLijn> bestelbonLijnen = new TreeSet<>();
        List<BigDecimal> totaalLijnen = new ArrayList<>();
        for (Map.Entry<Long, Long> entry : mandje.getIdsCount().entrySet()) {
            BigDecimal prijs = bierService.findPrijsById(entry.getKey());
            bestelbonLijnen.add(new BestelbonLijn(entry.getKey(), bierService.findNaamById(entry.getKey()), entry.getValue(), prijs));
            totaalLijnen.add(BigDecimal.valueOf(entry.getValue()).multiply(prijs));
        }
        var modelAndView = new ModelAndView("mandje");
        modelAndView.addObject("totaal", totaalLijnen.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        modelAndView.addObject("bestelbon", new Bestelbon(0, "","",null, (short) 0,"", bestelbonLijnen));
        return modelAndView;
    }
    @PostMapping("/toevoegen")
    public String bestelbon(@Valid Bestelbon bestelbon,Errors errors, RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            return "redirect:/mandje";
        }
        Set<@Valid BestelbonLijn> bestelbonlijnen = new TreeSet<>();
        for (Map.Entry<Long, Long> entry : mandje.getIdsCount().entrySet()) {
            BigDecimal prijs = bierService.findPrijsById(entry.getKey());
            bestelbonlijnen.add(new BestelbonLijn(entry.getKey(), bierService.findNaamById(entry.getKey()), entry.getValue(), prijs));
        }
        redirect.addAttribute("bestelbonId",bestelbonService.bestelbonCreeeren(bestelbon,bestelbonlijnen));
        mandje.maakLeeg();
        return "redirect:/mandje/toevoegen";
    }
    @GetMapping("/toevoegen")
    public ModelAndView toonBestelbon() {
        ModelAndView modelAndView = new ModelAndView("toevoegen");
        return modelAndView;
    }
}
