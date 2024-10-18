package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/infektionen")
public class InfektionController {

    @Autowired
    private InfektionService infektionService;

    @Autowired
    private ErregertypService erregertypService;

    // Anzeige der Liste aller Infektionen
    @GetMapping("/view")
    public String showAllInfektionen(Model model) {
        List<Infektion> infektionen = infektionService.getAllInfektionen();
        model.addAttribute("infektionen", infektionen); // Übergibt die Liste der Infektionen
        return "infektionen"; // verweist auf infektionen.html
    }

    // Anzeige des Formulars zum Anlegen einer neuen Infektion
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("infektion", new Infektion());
        model.addAttribute("erregertypen", erregertypService.getAllErregertypen()); // Liste der Erregertypen
        return "infektion-form"; // verweist auf infektion-form.html
    }


    // Speichern einer neuen Infektion mit den ausgewählten Erregertypen
    @PostMapping("/create")
    public String createInfektion(@ModelAttribute Infektion infektion, @RequestParam List<Long> erregertypIds) {
        List<Erregertyp> erregertypen = erregertypService.findAllByIds(erregertypIds); // Finde alle ausgewählten Erregertypen
        infektion.setErregertypen(erregertypen); // Weise die Erregertypen der Infektion zu
        infektionService.saveInfektion(infektion); // Speichere die Infektion
        return "redirect:/infektionen/view"; // Weiterleitung zur Übersicht nach dem Speichern
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Infektion> optionalInfektion = infektionService.getInfektionById(id);
        if (optionalInfektion.isPresent()) {
            model.addAttribute("infektion", optionalInfektion.get());
            model.addAttribute("erregertypen", erregertypService.getAllErregertypen()); // Füge die Erregertypen hinzu
            return "infektion-form"; // verweist auf infektion-form.html (zum Bearbeiten)
        } else {
            return "redirect:/infektionen/view"; // Wenn keine Infektion gefunden wurde, zur Liste zurückkehren
        }
    }


    // Aktualisieren einer Infektion
    @PostMapping("/update/{id}")
    public String updateInfektion(@PathVariable Long id, @ModelAttribute Infektion infektion, @RequestParam List<Long> erregertypIds) {
        List<Erregertyp> erregertypen = erregertypService.findAllByIds(erregertypIds); // Finde ausgewählte Erregertypen
        infektion.setErregertypen(erregertypen); // Setze die Erregertypen für die Infektion
        infektionService.updateInfektion(id, infektion); // Speichere die Änderungen
        return "redirect:/infektionen/view"; // Nach dem Update zur Übersicht weiterleiten
    }

    // Löschen einer Infektion
    @GetMapping("/delete/{id}")
    public String deleteInfektion(@PathVariable Long id) {
        infektionService.deleteInfektion(id); // Löschen der Infektion
        return "redirect:/infektionen/view"; // Nach dem Löschen zur Liste weiterleiten
    }

    // Abrufen einer einzelnen Infektion per API
    @GetMapping("/{id}")
    public ResponseEntity<Infektion> getInfektionById(@PathVariable Long id) {
        Optional<Infektion> infektion = infektionService.getInfektionById(id);
        return infektion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
