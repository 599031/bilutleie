package bilutleie.persistence.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Utleiekontor")
public class Utleiekontor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utleiekontor_kontornr_seq")
    @Column(name = "kontornr")
    private BigInteger kontornr;

    @Column(name = "tlf")
    private String tlf;

    @Column(name = "kontoradresse")
    private String kontoradresse;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bilutleieselskap")
    private Bilutleieselskap bilutleieselskap;

    public BigInteger getKontornr() {
        return kontornr;
    }

    public void setKontornr(BigInteger kontornr) {
        this.kontornr = kontornr;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getKontoradresse() {
        return kontoradresse;
    }

    public void setKontoradresse(String kontoradresse) {
        this.kontoradresse = kontoradresse;
    }

    public Bilutleieselskap getBilutleieselskap() {
        return bilutleieselskap;
    }

    public void setBilutleieselskap(Bilutleieselskap bilutleieselskap) {
        this.bilutleieselskap = bilutleieselskap;
    }
}
