package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Anzeige des Login-Formulars
    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("login", new Login()); // Bindet ein leeres Login-Objekt für das Formular
        return "login"; // verweist auf login.html
    }

    // Verarbeiten der Login-Daten
    @PostMapping
    public String processLogin(@ModelAttribute Login login, Model model) {
        boolean isAuthenticated = loginService.authenticate(login.getUsername(), login.getPassword());

        if (isAuthenticated) {
            return "redirect:/gesamtuebersicht"; // Bei erfolgreichem Login zur Übersicht weiterleiten
        } else {
            model.addAttribute("error", "Ungültiger Benutzername oder Passwort."); // Fehlernachricht anzeigen
            return "login"; // Zurück zur Login-Seite
        }
    }
}
