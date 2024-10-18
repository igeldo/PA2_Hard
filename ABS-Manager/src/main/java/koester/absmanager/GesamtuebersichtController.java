package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gesamtuebersicht")
public class GesamtuebersichtController {

    @Autowired
    private BehandlungshinweisService behandlungshinweisService;

    @Autowired
    private InfektionService infektionService;

    @Autowired
    private TherapieService therapieService;

    @Autowired
    private MedikamentService medikamentService;

    @Autowired
    private ErregertypService erregertypService;

    @GetMapping("/view")
    public String showGesamtuebersicht(Model model) {
        List<Behandlungshinweis> behandlungshinweise = behandlungshinweisService.getAllBehandlungshinweise();
        model.addAttribute("behandlungshinweise", behandlungshinweise);
        return "gesamtuebersicht";  // Verweist auf gesamtuebersicht.html
    }
}

