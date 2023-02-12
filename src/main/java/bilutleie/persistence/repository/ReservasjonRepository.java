package bilutleie.persistence.repository;

import bilutleie.persistence.entity.Reservasjon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ReservasjonRepository extends JpaRepository<Reservasjon, BigInteger> {
}
