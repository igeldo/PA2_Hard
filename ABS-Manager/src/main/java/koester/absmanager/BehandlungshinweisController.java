package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/behandlungshinweise")
public class BehandlungshinweisController {

    @Autowired
    private BehandlungshinweisService behandlungshinweisService;
    @Autowired
    private InfektionService infektionService;

    @GetMapping("/view")
    public String showBehandlungshinweise(Model model) {
        List<Behandlungshinweis> behandlungshinweise = behandlungshinweisService.getAllBehandlungshinweise();
        List<Infektion> infektionen = infektionService.getAllInfektionen();  // Lade alle Infektionen
        model.addAttribute("behandlungshinweise", behandlungshinweise);
        model.addAttribute("infektionen", infektionen);  // Füge Infektionen hinzu
        model.addAttribute("behandlungshinweis", new Behandlungshinweis());
        return "behandlungshinweise";  // Verweist auf behandlungshinweise.html
    }

    @PostMapping("/create")
    public String createBehandlungshinweis(@ModelAttribute("behandlungshinweis") Behandlungshinweis behandlungshinweis) {
        behandlungshinweisService.createBehandlungshinweis(behandlungshinweis);
        return "redirect:/behandlungshinweise/view";
    }

    @GetMapping("/edit/{id}")
    public String editBehandlungshinweis(@PathVariable Long id, Model model) {
        Optional<Behandlungshinweis> behandlungshinweis = behandlungshinweisService.getBehandlungshinweisById(id);
        if (behandlungshinweis.isPresent()) {
            model.addAttribute("behandlungshinweis", behandlungshinweis.get());
            return "behandlungshinweise";
        } else {
            return "redirect:/behandlungshinweise/view";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteBehandlungshinweis(@PathVariable Long id) {
        behandlungshinweisService.deleteBehandlungshinweis(id);
        return "redirect:/behandlungshinweise/view";
    }
}
