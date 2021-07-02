package be.vdab.bierhuis.controllers;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {
    private final Mandje mandje;
    MyControllerAdvice(Mandje mandje) {
        this.mandje = mandje;
    }
    @ModelAttribute("mandje")
    void mandjeToevoegenAanModel(Model model) {
        model.addAttribute(mandje);
    }
}
