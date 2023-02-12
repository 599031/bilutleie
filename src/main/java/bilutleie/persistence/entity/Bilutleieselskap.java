package bilutleie.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bilutleieselskap")
public class Bilutleieselskap {

    @Id
    @Column(name = "navn")
    private String navn;

    @Column(name = "tlf")
    private String tlf;

    @Column(name = "firmaadresse")
    private String firmaadresse;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getFirmaadresse() {
        return firmaadresse;
    }

    public void setFirmaadresse(String firmaadresse) {
        this.firmaadresse = firmaadresse;
    }
}
