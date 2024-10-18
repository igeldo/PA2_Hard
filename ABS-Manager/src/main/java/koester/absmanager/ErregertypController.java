package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/erregertypen")
public class ErregertypController {

    @Autowired
    private ErregertypService erregertypService;

    // Anzeige der Liste aller Erregertypen
    @GetMapping("/view")
    public String showErregertypen(Model model) {
        List<Erregertyp> erregertypen = erregertypService.getAllErregertypen();
        model.addAttribute("erregertypen", erregertypen);
        model.addAttribute("erregertyp", new Erregertyp()); // Für das Formular zur Erstellung eines neuen Erregertyps
        return "erregertypen"; // verweist auf erregertypen.html
    }

    // Anzeige eines Formulars zur Erstellung eines neuen Erregertyps
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("erregertyp", new Erregertyp());
        return "erregertyp-form"; // verweist auf erregertyp-form.html
    }

    // Erstellung eines neuen Erregertyps
    @PostMapping("/create")
    public String createErregertyp(@ModelAttribute Erregertyp erregertyp) {
        erregertypService.createErregertyp(erregertyp);
        return "redirect:/erregertypen/view"; // Nach dem Speichern zurück zur Übersicht
    }

    // Anzeige eines Formulars zur Bearbeitung eines bestehenden Erregertyps
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Erregertyp> erregertyp = erregertypService.getErregertypById(id);
        if (erregertyp.isPresent()) {
            model.addAttribute("erregertyp", erregertyp.get());
            return "erregertyp-form"; // verweist auf das Formular zur Bearbeitung
        } else {
            return "redirect:/erregertypen/view"; // Falls nicht gefunden, zurück zur Übersicht
        }
    }

    // Aktualisierung eines bestehenden Erregertyps
    @PostMapping("/update/{id}")
    public String updateErregertyp(@PathVariable Long id, @ModelAttribute Erregertyp erregertypDetails) {
        erregertypService.updateErregertyp(id, erregertypDetails);
        return "redirect:/erregertypen/view"; // Nach dem Update zurück zur Übersicht
    }

    // Löschen eines Erregertyps
    @GetMapping("/delete/{id}")
    public String deleteErregertyp(@PathVariable Long id) {
        erregertypService.deleteErregertyp(id);
        return "redirect:/erregertypen/view"; // Nach dem Löschen zurück zur Übersicht
    }
}
