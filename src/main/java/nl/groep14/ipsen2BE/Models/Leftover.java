package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leftover")
public class Leftover {

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
   private double metrage;

   @Basic
   @Column(name = "gewicht")
   private double gewicht;

   @Basic
   @Column(name = "date_cut")
   private Date dateCut;

    @Basic
    @Column(name = "type")
    private String type;

    public Leftover(String artikelnummer, boolean processed, double metrage, double gewicht, Date dateCut) {
        this.artikelnummer = artikelnummer;
        this.processed = processed;
        this.metrage = metrage;
        this.gewicht = gewicht;
        this.dateCut = dateCut;
    }

    public Leftover() {

    }

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

    public double getMetrage() {
        return metrage;
    }

    public void setMetrage(double metrage) {
        this.metrage = metrage;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
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
        return "Leftover{" +
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