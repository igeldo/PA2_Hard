package koester.absmanager;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BehandlungshinweisService {

    @Autowired
    private BehandlungshinweisRepository behandlungshinweisRepository;

    public List<Behandlungshinweis> getAllBehandlungshinweise() {
        return behandlungshinweisRepository.findAll();
    }

    public Optional<Behandlungshinweis> getBehandlungshinweisById(Long id) {
        return behandlungshinweisRepository.findById(id);
    }

    public Behandlungshinweis createBehandlungshinweis(Behandlungshinweis behandlungshinweis) {
        return behandlungshinweisRepository.save(behandlungshinweis);
    }

    public Behandlungshinweis updateBehandlungshinweis(Long id, Behandlungshinweis behandlungshinweisDetails) {
        Optional<Behandlungshinweis> optionalBehandlungshinweis = behandlungshinweisRepository.findById(id);
        if (optionalBehandlungshinweis.isPresent()) {
            Behandlungshinweis behandlungshinweis = optionalBehandlungshinweis.get();
            behandlungshinweis.setHinweis(behandlungshinweisDetails.getHinweis());
            behandlungshinweis.setZusatzMassnahmen(behandlungshinweisDetails.getZusatzMassnahmen());
            behandlungshinweis.setInfektion(behandlungshinweisDetails.getInfektion());
            return behandlungshinweisRepository.save(behandlungshinweis);
        }
        return null;
    }

    public void deleteBehandlungshinweis(Long id) {
        behandlungshinweisRepository.deleteById(id);
    }
}
