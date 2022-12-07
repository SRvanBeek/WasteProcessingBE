package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cutwaste")
public class Cutwaste {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true, nullable = false)
   private long id;

   @Basic
   @Column(name = "artikelnummer")
   private String artikelnummer;

   @Basic
   @Column(name = "processed")
   private boolean processed;

   @Basic
   @Column(name = "metrage")
   private String metrage;

   @Basic
   @Column(name = "gewicht")
   private String gewicht;

   @Basic
   @Column(name = "date_cut")
   private Date dateCut;

    @Basic
    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public boolean getProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getMetrage() {
        return metrage;
    }

    public void setMetrage(String metrage) {
        this.metrage = metrage;
    }

    public String getGewicht() {
        return gewicht;
    }

    public void setGewicht(String gewicht) {
        this.gewicht = gewicht;
    }

    public Date getDateCut() {
        return dateCut;
    }

    public void setDateCut(Date dateCut) {
        this.dateCut = dateCut;
    }

    @Override
    public String toString() {
        return "Cutwaste{" +
                "id=" + id +
                ", artikelnummer='" + artikelnummer + '\'' +
                ", processed='" + processed + '\'' +
                ", metrage='" + metrage + '\'' +
                ", gewicht='" + gewicht + '\'' +
                ", dateCut=" + dateCut +
                ", type='" + type + '\'' +
                '}';
    }
}
