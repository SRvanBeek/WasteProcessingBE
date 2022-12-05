package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

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
   private String processed;

   @Basic
   @Column(name = "metrage")
   private String metrage;

   @Basic
   @Column(name = "gewicht")
   private String gewicht;

   @Basic
   @Column(name = "tododate")
   private String tododate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
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

    public String getTododate() {
        return tododate;
    }

    public void setTododate(String tododate) {
        this.tododate = tododate;
    }

    @Override
    public String toString() {
        return "Cutwaste{" +
                "id=" + id +
                ", artikelnummer='" + artikelnummer + '\'' +
                ", processed='" + processed + '\'' +
                ", metrage='" + metrage + '\'' +
                ", gewicht='" + gewicht + '\'' +
                ", tododate='" + tododate + '\'' +
                '}';
    }
}
