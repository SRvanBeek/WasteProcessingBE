package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

/**
 * Article is the model of the Article entity.
 * @author Dino Yang
 */
@Entity
@Table(name = "artikel")
public class Article {
    @Id
    @Column(name = "artikelnummer", unique = true, nullable = false)
    private String artikelnummer;
    @Basic
    @Column(name = "leverancier")
    private String leverancier;

    @Basic
    @Column(name = "productgroep")
    private String productgroep;

    @Basic
    @Column(name = "eancode")
    private String eancode;

    @Basic
    @Column(name = "omschrijving")
    private String omschrijving;

    @Basic
    @Column(name = "kleur")
    private int kleur;

    @Basic
    @Column(name = "stofbreedte")
    private int stofbreedte;

    @Basic
    @Column(name = "patroonlengte")
    private int patroonlengte;

    @Basic
    @Column(name = "patroonbreedte")
    private int patroonbreedte;

    @Basic
    @Column(name = "soort")
    private String soort;

    @Basic
    @Column(name = "opmaak")
    private String opmaak;

    @Basic
    @Column(name = "wascode")
    private String wascode;

    @Basic
    @Column(name = "samenstelling")
    private String samenstelling;

    @Basic
    @Column(name = "gewicht")
    private double gewicht;

    @Basic
    @Column(name = "niet_kantelbaar")
    private String nietKantelbaar;

    @Basic
    @Column(name = "stock_rl")
    private String stockRl;

    @Basic
    @Column(name = "min")
    private int min;


    public String getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public String getLeverancier() {
        return leverancier;
    }

    public void setLeverancier(String leverancier) {
        this.leverancier = leverancier;
    }

    public String getProductgroep() {
        return productgroep;
    }

    public void setProductgroep(String productgroep) {
        this.productgroep = productgroep;
    }

    public String getEancode() {
        return eancode;
    }

    public void setEancode(String eancode) {
        this.eancode = eancode;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getKleur() {
        return kleur;
    }

    public void setKleur(int kleur) {
        this.kleur = kleur;
    }

    public int getStofbreedte() {
        return stofbreedte;
    }

    public void setStofbreedte(int stofbreedte) {
        this.stofbreedte = stofbreedte;
    }

    public int getPatroonlengte() {
        return patroonlengte;
    }

    public void setPatroonlengte(int patroonlengte) {
        this.patroonlengte = patroonlengte;
    }

    public int getPatroonbreedte() {
        return patroonbreedte;
    }

    public void setPatroonbreedte(int patroonbreedte) {
        this.patroonbreedte = patroonbreedte;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public String getOpmaak() {
        return opmaak;
    }

    public void setOpmaak(String opmaak) {
        this.opmaak = opmaak;
    }

    public String getWascode() {
        return wascode;
    }

    public void setWascode(String wascode) {
        this.wascode = wascode;
    }

    public String getSamenstelling() {
        return samenstelling;
    }

    public void setSamenstelling(String samenstelling) {
        this.samenstelling = samenstelling;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public String getNietKantelbaar() {
        return nietKantelbaar;
    }

    public void setNietKantelbaar(String nietKantelbaar) {
        this.nietKantelbaar = nietKantelbaar;
    }

    public String getStockRl() {
        return stockRl;
    }

    public void setStockRl(String stockRl) {
        this.stockRl = stockRl;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artikelnummer='" + artikelnummer + '\'' +
                ", leverancier='" + leverancier + '\'' +
                ", productgroep='" + productgroep + '\'' +
                ", eancode='" + eancode + '\'' +
                ", omschrijving='" + omschrijving + '\'' +
                ", kleur=" + kleur +
                ", stofbreedte=" + stofbreedte +
                ", patroonlengte=" + patroonlengte +
                ", patroonbreedte=" + patroonbreedte +
                ", soort='" + soort + '\'' +
                ", opmaak='" + opmaak + '\'' +
                ", wascode='" + wascode + '\'' +
                ", samenstelling='" + samenstelling + '\'' +
                ", gewicht=" + gewicht +
                ", nietKantelbaar='" + nietKantelbaar + '\'' +
                ", stockRl='" + stockRl + '\'' +
                ", min=" + min +
                '}';
    }
}
