package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Utleiekontor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UtleiekontorRepository extends JpaRepository<Utleiekontor, BigInteger> {
}
