package bilutleie.persistence.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Kunde")
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kunde_kundenr_seq")
    @Column(name = "kundenummer")
    private BigInteger kundenummer;

    @Column(name = "fornavn")
    private String fornavn;

    @Column(name = "etternavn")
    private String etternavn;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tlf")
    private String tlf;

    @Column(name = "kredittkort")
    private BigInteger kredittkort;

    public BigInteger getKundenummer() {
        return kundenummer;
    }

    public void setKundenummer(BigInteger kundenummer) {
        this.kundenummer = kundenummer;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public BigInteger getKredittkort() {
        return kredittkort;
    }

    public void setKredittkort(BigInteger kredittkort) {
        this.kredittkort = kredittkort;
    }
}
