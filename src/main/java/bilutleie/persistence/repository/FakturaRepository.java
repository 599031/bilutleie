package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Faktura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, BigInteger> {
}
