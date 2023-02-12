package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Bilutleieselskap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilutleieselskapRepository extends JpaRepository<Bilutleieselskap, String> {
}
