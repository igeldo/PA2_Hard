package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    // Methode, um einen Benutzer anhand des Benutzernamens zu finden
    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    // Methode, um alle Benutzer abzurufen
    public List<Login> getAllUsers() {
        return loginRepository.findAll();
    }

    // Methode, um einen neuen Benutzer anzulegen
    public Login createUser(Login login) {
        return loginRepository.save(login);
    }

    // Methode, um einen bestehenden Benutzer zu aktualisieren
    public Login updateUser(Long id, Login loginDetails) {
        Optional<Login> optionalLogin = loginRepository.findById(id);
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            login.setUsername(loginDetails.getUsername());
            login.setPassword(loginDetails.getPassword()); // Hashing sollte hier angewendet werden
            return loginRepository.save(login);
        } else {
            return null; // oder wirf eine Exception
        }
    }

    // Methode, um einen Benutzer zu löschen
    public void deleteUser(Long id) {
        loginRepository.deleteById(id);
    }

    // Authentifizierungsmethode
    public boolean authenticate(String username, String password) {
        Optional<Login> optionalLogin = loginRepository.findByUsername(username);
        if (optionalLogin.isPresent()) {
            Login login = optionalLogin.get();
            // Passwortprüfung (z. B. Hash-Vergleich in einer realen Anwendung)
            return login.getPassword().equals(password); // In einer echten Anwendung sollte dies mit Hashing erfolgen
        }
        return false;
    }
}
