package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

@Entity
@Table(name = "artikel", schema = "vdl")
public class Article {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "artikel_ID")
    private Long artikelId;
    @Basic
    @Column(name = "user_ID")
    private int userId;

    @Basic
    @Column(name = "customer_ID")
    private int customerId;
    @Basic
    @Column(name = "artikelnummer")
    private String artikelnummer;

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
    private String kleur;
    @Basic
    @Column(name = "breedte")
    private int breedte;
    @Basic
    @Column(name = "patroonbreedte")
    private int patroonbreedte;
    @Basic
    @Column(name = "patroonlengte")
    private int patroonlengte;
    @Basic
    @Column(name = "soort")
    private String soort;
    @Basic
    @Column(name = "opmaak")
    private String opmaak;
    @Basic
    @Column(name = "verkoop_prijs")
    private float verkoopPrijs;
    @Basic
    @Column(name = "aankoop_prijs")
    private float aankoopPrijs;
    @Basic
    @Column(name = "wassymbolen")
    private String wassymbolen;
    @Basic
    @Column(name = "samenstelling")
    private String samenstelling;

    public Long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Long artikelId) {
        this.artikelId = artikelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getArtikelnummer() {
        return artikelnummer;
    }

    public void setArtikelnummer(String artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public int getBreedte() {
        return breedte;
    }

    public void setBreedte(int breedte) {
        this.breedte = breedte;
    }

    public int getPatroonbreedte() {
        return patroonbreedte;
    }

    public void setPatroonbreedte(int patroonbreedte) {
        this.patroonbreedte = patroonbreedte;
    }

    public int getPatroonlengte() {
        return patroonlengte;
    }

    public void setPatroonlengte(int patroonlengte) {
        this.patroonlengte = patroonlengte;
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

    public float getVerkoopPrijs() {
        return verkoopPrijs;
    }

    public void setVerkoopPrijs(float verkoopPrijs) {
        this.verkoopPrijs = verkoopPrijs;
    }

    public float getAankoopPrijs() {
        return aankoopPrijs;
    }

    public void setAankoopPrijs(float aankoopPrijs) {
        this.aankoopPrijs = aankoopPrijs;
    }

    public String getWassymbolen() {
        return wassymbolen;
    }

    public void setWassymbolen(String wassymbolen) {
        this.wassymbolen = wassymbolen;
    }

    public String getSamenstelling() {
        return samenstelling;
    }

    public void setSamenstelling(String samenstelling) {
        this.samenstelling = samenstelling;
    }

    @Override
    public String toString() {
        return "Article{" +
                "artikelId=" + artikelId +
                ", userId=" + userId +
                ", customerId=" + customerId +
                ", artikelnummer='" + artikelnummer + '\'' +
                ", productgroep='" + productgroep + '\'' +
                ", eancode='" + eancode + '\'' +
                ", omschrijving='" + omschrijving + '\'' +
                ", kleur='" + kleur + '\'' +
                ", breedte=" + breedte +
                ", patroonbreedte=" + patroonbreedte +
                ", patroonlengte=" + patroonlengte +
                ", soort='" + soort + '\'' +
                ", opmaak='" + opmaak + '\'' +
                ", verkoopPrijs=" + verkoopPrijs +
                ", aankoopPrijs=" + aankoopPrijs +
                ", wassymbolen='" + wassymbolen + '\'' +
                ", samenstelling='" + samenstelling + '\'' +
                '}';
    }
}
