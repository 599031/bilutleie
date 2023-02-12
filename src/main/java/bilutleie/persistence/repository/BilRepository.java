package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Bil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilRepository extends JpaRepository<Bil, String> {
}
