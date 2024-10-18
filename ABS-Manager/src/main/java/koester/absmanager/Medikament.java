package koester.absmanager;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Medikament {

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
    private String dosierung;

    @Setter
    @Getter
    @ManyToMany(mappedBy = "medikamente")
    private List<Therapie> therapien;


}
