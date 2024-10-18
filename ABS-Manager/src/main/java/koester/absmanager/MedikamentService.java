package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedikamentService {

    @Autowired
    private MedikamentRepository medikamentRepository;

    public List<Medikament> getAllMedikamente() {
        return medikamentRepository.findAll();
    }

    public Optional<Medikament> getMedikamentById(Long id) {
        return medikamentRepository.findById(id);
    }

    public Medikament createMedikament(Medikament medikament) {
        return medikamentRepository.save(medikament);
    }

    public Medikament updateMedikament(Long id, Medikament medikamentDetails) {
        Optional<Medikament> optionalMedikament = medikamentRepository.findById(id);
        if (optionalMedikament.isPresent()) {
            Medikament medikament = optionalMedikament.get();
            medikament.setName(medikamentDetails.getName());
            medikament.setDosierung(medikamentDetails.getDosierung());
            medikament.setTherapien(medikamentDetails.getTherapien());
            return medikamentRepository.save(medikament);
        }
        return null;
    }

    public void deleteMedikament(Long id) {
        medikamentRepository.deleteById(id);
    }
}
