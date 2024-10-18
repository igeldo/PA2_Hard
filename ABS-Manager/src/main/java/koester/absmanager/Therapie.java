package koester.absmanager;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Therapie {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private Integer dauer; // Dauer der Therapie in Tagen

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "therapie_medikament",
            joinColumns = @JoinColumn(name = "therapie_id"),
            inverseJoinColumns = @JoinColumn(name = "medikament_id"))
    private List<Medikament> medikamente;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "infektion_id", nullable = false)
    private Infektion infektion;

}

