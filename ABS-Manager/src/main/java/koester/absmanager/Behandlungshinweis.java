package koester.absmanager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Behandlungshinweis {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String hinweis;

    @Setter
    @Getter
    private String zusatzMassnahmen;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "infektion_id", nullable = false)
    private Infektion infektion;

}
