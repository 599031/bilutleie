package bilutleie.service;

import bilutleie.persistence.entity.Kunde;
import bilutleie.persistence.repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class KundeService {

    private final KundeRepository kundeRepository;

    @Autowired
    public KundeService(KundeRepository kundeRepository) {
        this.kundeRepository = kundeRepository;
    }

    public Kunde opprettKunde(String fornavn, String etternavn, String adresse, String tlf, BigInteger kredittkort) {
        Kunde kunde = new Kunde();
        kunde.setFornavn(fornavn);
        kunde.setEtternavn(etternavn);
        kunde.setAdresse(adresse);
        kunde.setTlf(tlf);
        kunde.setKredittkort(kredittkort);

        return kunde;
    }

    @Transactional
    public Kunde lagreKunde(Kunde kunde) {
       return kundeRepository.save(kunde);
    }

    @Transactional
    public Kunde opprettOgLagreKunde(String fornavn, String etternavn, String adresse, String tlf, BigInteger kredittkort) {
        Kunde kunde = opprettKunde(fornavn, etternavn, adresse, tlf, kredittkort);
        return lagreKunde(kunde);
    }

    public Kunde hentKunde(BigInteger kundenr) {
        Optional<Kunde> optional = kundeRepository.findById(kundenr);
        return optional.orElse(null);
    }

    public boolean endreKort(BigInteger kundenr, BigInteger nyttKort) {
        Kunde kunde = hentKunde(kundenr);
        if(kunde == null) {
            return false;
        }
        kunde.setKredittkort(nyttKort);
        lagreKunde(kunde);
        return true;
    }

}
