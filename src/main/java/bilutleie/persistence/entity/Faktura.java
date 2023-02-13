package bilutleie.persistence.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "Faktura")
public class Faktura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faktura_fakturanr_seq")
    @Column(name = "fakturanr")
    private BigInteger fakturanr;

    @Column(name = "antallTimer")
    private Integer antallTimer;

    @Column(name = "kmKjoert")
    private Integer kmKjoert;

    @Column(name = "pris")
    private Float pris;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "regnr")
    private Bil bil;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kundenr")
    private Kunde kunde;

    public BigInteger getFakturanr() {
        return fakturanr;
    }

    public void setFakturanr(BigInteger fakturanr) {
        this.fakturanr = fakturanr;
    }

    public Integer getAntallTimer() {
        return antallTimer;
    }

    public void setAntallTimer(Integer antallTimer) {
        this.antallTimer = antallTimer;
    }

    public Integer getKmKjoert() {
        return kmKjoert;
    }

    public void setKmKjoert(Integer kmKjoert) {
        this.kmKjoert = kmKjoert;
    }

    public Float getPris() {
        return pris;
    }

    public void setPris(Float pris) {
        this.pris = pris;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}
