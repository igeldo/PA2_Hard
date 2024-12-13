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
    @Autowired
    private InfektionsartService infektionsartService;

    // Anzeige der Liste aller Infektionen
    @GetMapping("/view")
    public String showAllInfektionen(Model model) {
        List<Infektion> infektionen = infektionService.getAllInfektionen();
        System.out.println("Infektionenliste: " + infektionen);
        model.addAttribute("infektionen", infektionen); // Übergibt die Liste der Infektionen

        List<Erregertyp> erregertypen = erregertypService.getAllErregertypen();
        model.addAttribute("infektionen", infektionen); // Übergibt die Liste der Infektionen

        //model.addAttribute("infektionen", new Infektion());
        return "infektion-form"; // verweist auf infektionen.html
    }

    // Anzeige des Formulars zum Anlegen einer neuen Infektion
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("infektion", new Infektion());
        model.addAttribute("erregertypen", erregertypService.getAllErregertypen());
        model.addAttribute("infektionsarten", infektionsartService.findAll());
        model.addAttribute("infektionen", infektionService.getAllInfektionen()); // Liste hinzufügen
        return "infektion-form";
    }

    @GetMapping("/edit/{id}")
    //hier fehlt noch mehr (Laden der bestehenden Funktion, Laden aller anderen Daten wie beim Anzeigen)
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Infektion> optionalInfektion = infektionService.getInfektionById(id);
        if (optionalInfektion.isPresent()) {
            model.addAttribute("infektion", optionalInfektion.get());
            model.addAttribute("erregertypen", erregertypService.getAllErregertypen()); // Füge die Erregertypen hinzu
            return "infektion-form"; // verweist auf infektion-form.html (zum Bearbeiten)
        } else {
            return "redirect:/gesamtuebersicht/view"; // Wenn keine Infektion gefunden wurde, zur Liste zurückkehren
        }
    }


    // Aktualisieren einer Infektion
    @PostMapping("/update/{id}")
    public String updateInfektion(@PathVariable Long id, @ModelAttribute Infektion infektion, @RequestParam List<Long> erregertypIds) {
        List<Erregertyp> erregertypen = erregertypService.findAllByIds(erregertypIds); // Finde ausgewählte Erregertypen
        infektion.setErregertypen(erregertypen); // Setze die Erregertypen für die Infektion
        infektionService.updateInfektion(id, infektion); // Speichere die Änderungen
        return "redirect:/infektionen/create"; // Nach dem Update zur Übersicht weiterleiten
    }

    // Löschen einer Infektion
    @GetMapping("/delete/{id}")
    public String deleteInfektion(@PathVariable Long id) {
        infektionService.deleteInfektion(id); // Löschen der Infektion
        return "redirect:/infektionen/create"; // Nach dem Löschen zur Liste weiterleiten
    }
    //    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteInfektion(@PathVariable Long id) {
//        infektionService.deleteInfektion(id);
//        return ResponseEntity.noContent().build();
//    }
    // Abrufen einer einzelnen Infektion per API
    @GetMapping("/{id}")
    public ResponseEntity<Infektion> getInfektionById(@PathVariable Long id) {
        Optional<Infektion> infektion = infektionService.getInfektionById(id);
        return infektion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
