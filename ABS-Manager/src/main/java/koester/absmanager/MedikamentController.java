package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/medikamente")
public class MedikamentController {

    @Autowired
    private MedikamentService medikamentService;

    @GetMapping("/view")
    public String showMedikamente(Model model) {
        List<Medikament> medikamente = medikamentService.getAllMedikamente();
        model.addAttribute("medikamente", medikamente);
        model.addAttribute("medikament", new Medikament());
        return "medikamente";  // Verweist auf medikamente.html
    }

    @PostMapping("/create")
    public String createMedikament(@ModelAttribute("medikament") Medikament medikament) {
        medikamentService.createMedikament(medikament);
        return "redirect:/medikamente/view";
    }

    @GetMapping("/edit/{id}")
    public String editMedikament(@PathVariable Long id, Model model) {
        Optional<Medikament> medikament = medikamentService.getMedikamentById(id);
        if (medikament.isPresent()) {
            model.addAttribute("medikament", medikament.get());
            return "medikamente";
        } else {
            return "redirect:/medikamente/view";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteMedikament(@PathVariable Long id) {
        medikamentService.deleteMedikament(id);
        return "redirect:/medikamente/view";
    }
}
