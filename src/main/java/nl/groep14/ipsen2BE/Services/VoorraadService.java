package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Models.Voorraad;
import org.springframework.stereotype.Service;

@Service
public class VoorraadService {
    private final VoorraadDAO voorraadDAO;
    private final CutWasteDAO cutWasteDAO;
    private final ArticleDAO articleDAO;

    public VoorraadService(VoorraadDAO voorraadDAO, CutWasteDAO cutWasteDAO, ArticleDAO articleDAO) {
        this.voorraadDAO = voorraadDAO;
        this.cutWasteDAO = cutWasteDAO;
        this.articleDAO = articleDAO;
    }
    public Article OrderToArticle(Long Id) {
        Voorraad voorraad = voorraadDAO.getVoorraadByID(Id).get();
        Cutwaste cutwaste = cutWasteDAO.getByID(voorraad.getCutwasteID()).get();
        return articleDAO.getArticleByArtikelNummer(cutwaste.getArtikelnummer()).get();
    }
}
