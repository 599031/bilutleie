package bilutleie.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "Bil")
public class Bil {

    @Id
    @Column(name = "regnr")
    private String regnr;

    @Column(name = "merke")
    private String merke;

    @Column(name = "modell")
    private String modell;

    @Column(name = "farge")
    private String farge;

    @Column(name = "utleiegruppe")
    private String utleiebruppe;

    @Column(name = "ledig")
    private Boolean ledig;

    @Column(name = "km")
    private Integer km;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "utleiekontor")
    private Utleiekontor utleiekontor;

    public String getRegnr() {
        return regnr;
    }

    public void setRegnr(String regnr) {
        this.regnr = regnr;
    }

    public String getMerke() {
        return merke;
    }

    public void setMerke(String merke) {
        this.merke = merke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getFarge() {
        return farge;
    }

    public void setFarge(String farge) {
        this.farge = farge;
    }

    public String getUtleiebruppe() {
        return utleiebruppe;
    }

    public void setUtleiebruppe(String utleiebruppe) {
        this.utleiebruppe = utleiebruppe;
    }

    public Boolean getLedig() {
        return ledig;
    }

    public void setLedig(Boolean ledig) {
        this.ledig = ledig;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Utleiekontor getUtleiekontor() {
        return utleiekontor;
    }

    public void setUtleiekontor(Utleiekontor utleiekontor) {
        this.utleiekontor = utleiekontor;
    }
}
