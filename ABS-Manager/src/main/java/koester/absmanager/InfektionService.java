package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfektionService {

    @Autowired
    private InfektionRepository infektionRepository;

    // Methode, um alle Infektionen abzurufen
    public List<Infektion> getAllInfektionen() {
        return infektionRepository.findAll();
    }

    // Methode, um eine bestimmte Infektion nach ID zu finden
    public Optional<Infektion> getInfektionById(Long id) {
        return infektionRepository.findById(id);
    }

    // Methode, um eine neue Infektion zu speichern
    public Infektion saveInfektion(Infektion infektion) {
        return infektionRepository.save(infektion);
    }

    // Methode, um eine bestehende Infektion zu aktualisieren
    public Infektion updateInfektion(Long id, Infektion infektionDetails) {
        Optional<Infektion> optionalInfektion = infektionRepository.findById(id);
        if (optionalInfektion.isPresent()) {
            Infektion infektion = optionalInfektion.get();
            infektion.setName(infektionDetails.getName());
            infektion.setErregertypen(infektionDetails.getErregertypen());  // Setze die Erregertypen
            return infektionRepository.save(infektion);
        } else {
            return null; // oder wirf eine Exception, wenn die Infektion nicht existiert
        }
    }

    // Methode, um eine Infektion zu löschen
    public void deleteInfektion(Long id) {
        infektionRepository.deleteById(id);
    }
}
