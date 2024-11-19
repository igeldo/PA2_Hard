package koester.absmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    // Methode zur Suche eines Benutzers anhand des Benutzernamens
    Optional<Login> findByUsername(String username);

}
