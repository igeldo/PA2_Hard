package koester.absmanager;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfektionsartService {

    private final InfektionsartRepository infektionsartRepository;

    public InfektionsartService(InfektionsartRepository infektionsartRepository) {
        this.infektionsartRepository = infektionsartRepository;
    }

    public List<Infektionsart> findAll() {
        return infektionsartRepository.findAll();
    }

    public Infektionsart findById(Long id) {
        return infektionsartRepository.findById(id).orElse(null);
    }

    public Infektionsart save(Infektionsart infektionsart) {
        return infektionsartRepository.save(infektionsart);
    }

    public void deleteById(Long id) {
        infektionsartRepository.deleteById(id);
    }
}