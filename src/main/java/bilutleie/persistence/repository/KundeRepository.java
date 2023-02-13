package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface KundeRepository extends JpaRepository<Kunde, BigInteger> {
}
