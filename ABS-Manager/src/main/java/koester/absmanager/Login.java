package koester.absmanager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Login {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    private String password;

}
