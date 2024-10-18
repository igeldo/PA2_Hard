package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/therapien")
public class TherapieController {

    @Autowired
    private TherapieService therapieService;
    @Autowired
    private InfektionService infektionService;
    @Autowired
    private MedikamentRepository medikamentRepository;
    @Autowired
    private InfektionRepository infektionRepository;
    @Autowired
    private TherapieRepository therapieRepository;

    @GetMapping("/view")
    public String showTherapien(Model model) {
        List<Therapie> therapien = therapieService.getAllTherapien();
        List<Infektion> infektionen = infektionService.getAllInfektionen();  // Lade alle Infektionen
        model.addAttribute("therapien", therapien);
        model.addAttribute("therapie", new Therapie());
        model.addAttribute("infektionen", infektionen);  // Übergib Infektionen an die View
        model.addAttribute("medikamente", medikamentRepository.findAll()); // Medikamente hinzufügen

        return "therapien";  // Verweist auf therapien.html
    }


    @PostMapping("/create")
    public String createTherapie(@ModelAttribute Therapie therapie, @RequestParam Long infektionId, @RequestParam List<Long> medikamentIds) {
        // Therapie erstellen und speichern
        Infektion infektion = infektionRepository.findById(infektionId).orElseThrow(() -> new IllegalArgumentException("Ungültige Infektions-ID: " + infektionId));
        therapie.setInfektion(infektion);
        List<Medikament> medikamente = medikamentRepository.findAllById(medikamentIds);
        therapie.setMedikamente(medikamente);
        therapieRepository.save(therapie);
        return "redirect:/therapien/view"; // Leitet zurück auf die Liste aller Therapien
    }


    @GetMapping("/edit/{id}")
    public String editTherapie(@PathVariable Long id, Model model) {
        Optional<Therapie> therapie = therapieService.getTherapieById(id);
        if (therapie.isPresent()) {
            model.addAttribute("therapie", therapie.get());
            return "therapien";
        } else {
            return "redirect:/therapien/view";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteTherapie(@PathVariable Long id) {
        therapieService.deleteTherapie(id);
        return "redirect:/therapien/view";
    }
}
