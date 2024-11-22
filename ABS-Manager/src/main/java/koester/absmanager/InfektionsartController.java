package koester.absmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infektionsarten")

public class InfektionsartController {
    @Autowired
    private InfektionsartRepository infektionsartRepository;

    @GetMapping
    public List<Infektionsart> getAllInfektionsarten() {
        return infektionsartRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Infektionsart createInfektionsart(Infektionsart infektionsart) {
        return infektionsartRepository.save(infektionsart);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Infektionsart> getInfektionsartById(@PathVariable Long id) {
        Optional<Infektionsart> infektionsart = infektionsartRepository.findById(id);
        return infektionsart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infektionsart> updateInfektionsart(@PathVariable Long id, @RequestBody Infektionsart updatedInfektionsart) {
        return infektionsartRepository.findById(id).map(infektionsart -> {
            infektionsart.setName(updatedInfektionsart.getName());
            return ResponseEntity.ok(infektionsartRepository.save(infektionsart));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfektionsart(@PathVariable Long id) {
        if (infektionsartRepository.existsById(id)) {
            infektionsartRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Controller
    @RequestMapping("/infektionsarten")
    public class InfektionsartViewController {
        @GetMapping("/view")
        public String viewInfektionsarten(Model model) {
            model.addAttribute("infektionsarten", infektionsartRepository.findAll());
            return "infektionsarten";
        }
    }


}