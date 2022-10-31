package nl.groep14.ipsen2BE.Models;


import java.util.ArrayList;

public class Label {
    private String naam;
    private long ordernummer;
    private ArrayList<String> categorie;
    private double metrage;
    private String samenstellingen;

    private ArrayList<String> referentie;

    private String vervoerder;

    private String productgroep;

    private String kleur;

    private long artikelnummer;

    private ArrayList<String> afleverlocatie;

    public Label(String naam, long ordernummer, ArrayList<String> categorie, double metrage, String samenstellingen) {
        this.naam = naam;
        this.ordernummer = ordernummer;
        this.categorie = categorie;
        this.metrage = metrage;
        this.samenstellingen = samenstellingen;
    }
    public Label(String naam, long ordernummer, double metrage, String productgroep, String kleur, long artikelnummer, ArrayList<String> afleverlocatie) {
        this.naam = naam;
        this.ordernummer = ordernummer;
        this.metrage = metrage;
        this.productgroep = productgroep;
        this.kleur = kleur;
        this.artikelnummer = artikelnummer;
        this.afleverlocatie = afleverlocatie;
    }

    public ArrayList<String> getReferentie() {
        return referentie;
    }

    public void setReferentie(ArrayList<String> referentie) {
        this.referentie = referentie;
    }

    public String getVervoerder() {
        return vervoerder;
    }

    public void setVervoerder(String vervoerder) {
        this.vervoerder = vervoerder;
    }

    public String getProductgroep() {
        return productgroep;
    }

    public void setProductgroep(String productgroep) {
        this.productgroep = productgroep;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public long getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(long artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public ArrayList<String> getAfleverlocatie() {
        return afleverlocatie;
    }

    public void setAfleverlocatie(ArrayList<String> afleverlocatie) {
        this.afleverlocatie = afleverlocatie;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public long getOrdernummer() {
        return ordernummer;
    }

    public void setOrdernummer(long ordernummer) {
        this.ordernummer = ordernummer;
    }

    public ArrayList<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<String> categorie) {
        this.categorie = categorie;
    }

    public double getMetrage() {
        return metrage;
    }

    public void setMetrage(double metrage) {
        this.metrage = metrage;
    }

    public String getSamenstellingen() {
        return samenstellingen;
    }

    public void setSamenstellingen(String samenstellingen) {
        this.samenstellingen = samenstellingen;
    }
}
