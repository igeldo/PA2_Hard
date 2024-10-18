package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ErregertypService {

    @Autowired
    private ErregertypRepository erregertypRepository;

    // Methode, um alle Erregertypen abzurufen
    public List<Erregertyp> getAllErregertypen() {
        return erregertypRepository.findAll();
    }

    // Methode, um einen bestimmten Erregertyp nach ID zu finden
    public Optional<Erregertyp> getErregertypById(Long id) {
        return erregertypRepository.findById(id);
    }

    // Methode, um Erregertypen anhand einer Liste von IDs zu finden
    public List<Erregertyp> findAllByIds(List<Long> ids) {
        return erregertypRepository.findAllById(ids);
    }

    // Methode, um einen neuen Erregertyp zu erstellen
    public Erregertyp createErregertyp(Erregertyp erregertyp) {
        return erregertypRepository.save(erregertyp);
    }

    // Methode, um einen bestehenden Erregertyp zu aktualisieren
    public Erregertyp updateErregertyp(Long id, Erregertyp erregertypDetails) {
        Optional<Erregertyp> optionalErregertyp = erregertypRepository.findById(id);
        if (optionalErregertyp.isPresent()) {
            Erregertyp erregertyp = optionalErregertyp.get();
            erregertyp.setName(erregertypDetails.getName());
            return erregertypRepository.save(erregertyp);
        } else {
            return null; // oder wirf eine Exception
        }
    }

    // Methode, um einen Erregertyp zu löschen
    public void deleteErregertyp(Long id) {
        erregertypRepository.deleteById(id);
    }
}
