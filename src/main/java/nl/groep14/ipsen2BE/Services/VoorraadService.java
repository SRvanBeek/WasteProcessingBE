package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VoorraadService {
    private final VoorraadDAO voorraadDAO;
    private final LeftoverDAO leftoverDAO;
    private final ArticleDAO articleDAO;

    public VoorraadService(VoorraadDAO voorraadDAO, LeftoverDAO leftoverDAO, ArticleDAO articleDAO) {
        this.voorraadDAO = voorraadDAO;
        this.leftoverDAO = leftoverDAO;
        this.articleDAO = articleDAO;
    }
    public ApiResponse OrderToArticle(Long Id) {
        Voorraad voorraad = voorraadDAO.getVoorraadByID(Id).get();
        Leftover leftover = leftoverDAO.getById(voorraad.getLeftoverId());
        return new ApiResponse(HttpStatus.ACCEPTED, articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer()));
    }
}
