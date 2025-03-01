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

    @GetMapping("/")  // Setzt "gesamtuebersicht.html" als Startseite
    public String showGesamtuebersicht() {
        return "gesamtuebersicht"; // Ohne .html, da Thymeleaf das automatisch ergänzt
    }
}

