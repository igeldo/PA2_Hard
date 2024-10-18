package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TherapieService {

    @Autowired
    private TherapieRepository therapieRepository;

    public List<Therapie> getAllTherapien() {
        return therapieRepository.findAll();
    }

    public Optional<Therapie> getTherapieById(Long id) {
        return therapieRepository.findById(id);
    }

    public Therapie createTherapie(Therapie therapie) {
        return therapieRepository.save(therapie);
    }

    public Therapie updateTherapie(Long id, Therapie therapieDetails) {
        Optional<Therapie> optionalTherapie = therapieRepository.findById(id);
        if (optionalTherapie.isPresent()) {
            Therapie therapie = optionalTherapie.get();
            therapie.setName(therapieDetails.getName());
            therapie.setDauer(therapieDetails.getDauer());
            therapie.setMedikamente(therapieDetails.getMedikamente());
            therapie.setInfektion(therapieDetails.getInfektion());
            return therapieRepository.save(therapie);
        }
        return null;
    }

    public void deleteTherapie(Long id) {
        therapieRepository.deleteById(id);
    }
}

