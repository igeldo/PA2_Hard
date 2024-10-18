package koester.absmanager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Infektion {

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
    @ManyToMany
    @JoinTable(
            name = "infektion_erregertyp",
            joinColumns = @JoinColumn(name = "infektion_id"),
            inverseJoinColumns = @JoinColumn(name = "erregertyp_id"))
    private List<Erregertyp> erregertypen = new ArrayList<>();


    @Setter
    @Getter
    @OneToMany(mappedBy = "infektion")
    private List<Therapie> therapien;


}