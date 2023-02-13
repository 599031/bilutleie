package bilutleie.service;

import bilutleie.persistence.entity.Bilutleieselskap;
import bilutleie.persistence.repository.BilutleieselskapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BilutleieselskapService {

    private final BilutleieselskapRepository bilutleieselskapRepository;

    @Autowired
    public BilutleieselskapService(BilutleieselskapRepository bilutleieselskapRepository) {
        this.bilutleieselskapRepository = bilutleieselskapRepository;
    }

    public Bilutleieselskap opprettBilutleieselskap(String navn, String tlf, String firmaadresse) {
        Bilutleieselskap bilutleieselskap = new Bilutleieselskap();
        bilutleieselskap.setNavn(navn);
        bilutleieselskap.setTlf(tlf);
        bilutleieselskap.setFirmaadresse(firmaadresse);

        return bilutleieselskap;
    }

    @Transactional
    public Bilutleieselskap lagreBilutleieselskap(Bilutleieselskap bilutleieselskap) {
        return bilutleieselskapRepository.save(bilutleieselskap);
    }

    @Transactional
    public Bilutleieselskap opprettOgLagreBilutleieselskap(String navn, String tlf, String firmaadresse) {
        Bilutleieselskap bilutleieselskap = opprettBilutleieselskap(navn, tlf, firmaadresse);
        return lagreBilutleieselskap(bilutleieselskap);
    }
}
