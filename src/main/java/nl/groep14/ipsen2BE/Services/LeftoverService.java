package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Leftover;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class LeftoverService {
    LeftoverDAO leftoverDAO;
    ArticleDAO articleDAO;


    public LeftoverService(LeftoverDAO leftoverDAO, ArticleDAO articleDAO) {
        this.leftoverDAO = leftoverDAO;
        this.articleDAO = articleDAO;
    }

    /**
     * this function returns an apirespose with the leftovers from the given customer id
     * @param id the given customer id
     * @return an api repsonse with the leftovers in it
     */
    public ApiResponse getLeftoverByCustomerId(String id){
        ArrayList<Article> article = articleDAO.getArticlesByCustomerId(id);
        ArrayList<Leftover> leftovers = new ArrayList<>();
        for (int i = 0; i < article.size(); i++) {
           ArrayList<Leftover> leftover = leftoverDAO.getByArtikelNummer(article.get(i).getArtikelnummer());
            for (int j = 0; j < leftover.size(); j++) {
                leftovers.add(leftover.get(j));
            }

        }
        return new ApiResponse(HttpStatus.ACCEPTED, leftovers);
    }

}
