package bilutleie.persistence.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Reservasjon")
public class Reservasjon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservasjon_reservasjonsid_seq")
    @Column(name = "reservasjonsId")
    private BigInteger reservasjonsId;

    @Column(name = "startDato")
    private ZonedDateTime startDato;

    @Column(name = "sluttDato")
    private ZonedDateTime sluttDato;

    @Column(name = "utleid")
    private Boolean utleid;

    @Column(name = "returnert")
    private Boolean returnert;

    @Column(name = "returdato")
    private ZonedDateTime returdato;

    @Column(name = "km")
    private Integer km;

    @Column(name = "nyKm")
    private Integer nyKm;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "regnr")
    private Bil bil;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bilutleieselskap")
    private Bilutleieselskap bilutleieselskap;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kundenr")
    private Kunde kunde;

    public BigInteger getReservasjonsId() {
        return reservasjonsId;
    }

    public void setReservasjonsId(BigInteger reservasjonsId) {
        this.reservasjonsId = reservasjonsId;
    }

    public ZonedDateTime getStartDato() {
        return startDato;
    }

    public void setStartDato(ZonedDateTime startDato) {
        this.startDato = startDato;
    }

    public ZonedDateTime getSluttDato() {
        return sluttDato;
    }

    public void setSluttDato(ZonedDateTime sluttDato) {
        this.sluttDato = sluttDato;
    }

    public Boolean getUtleid() {
        return utleid;
    }

    public void setUtleid(Boolean utleid) {
        this.utleid = utleid;
    }

    public Boolean getReturnert() {
        return returnert;
    }

    public void setReturnert(Boolean returnert) {
        this.returnert = returnert;
    }

    public ZonedDateTime getReturdato() {
        return returdato;
    }

    public void setReturdato(ZonedDateTime returdato) {
        this.returdato = returdato;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Integer getNyKm() {
        return nyKm;
    }

    public void setNyKm(Integer nyKm) {
        this.nyKm = nyKm;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public Bilutleieselskap getBilutleieselskap() {
        return bilutleieselskap;
    }

    public void setBilutleieselskap(Bilutleieselskap bilutleieselskap) {
        this.bilutleieselskap = bilutleieselskap;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}
