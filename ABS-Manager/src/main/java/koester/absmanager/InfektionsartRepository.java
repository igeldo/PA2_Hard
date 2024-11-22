package koester.absmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfektionsartRepository extends JpaRepository<Infektionsart, Long> {
}

