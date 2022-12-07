package nl.groep14.ipsen2BE.Controllers;


import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * ArticleController is the controller for the Waste Entity
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "api/article")
public class ArticleController {

    private final ArticleDAO articleDAO;

    public ArticleController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    /**
     * postArticle posts an Article into the database.
     *
     * @param article The ArticleModel that is received in the PostMethod
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postArticle(@RequestBody Article article) {
        this.articleDAO.saveToDatabase(article);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    /**
     * getAllArticles gets all Article entities in the database.
     *
     * @return List of Article entities
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getAllArticles() {
        List<Article> articles = this.articleDAO.getAll();
        return articles;
    }

    /**
     * getOneArticle gets one specific Article entity based on the id
     *
     * @param id is the id of the Article entity
     * @return Article entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getOneArticle(@PathVariable long id) {
        return this.articleDAO.getArticleByID(id).get();
    }

    /**
     * putOneArticle checks if a specific Article exist in the database. Then it changes the old Article into the new
     * Article and saves it to the database.
     *
     * @param id         is the id of the Article that needs to be changed
     * @param newArticle is the Article model with changes.
     * @return ApiResponse with a corresponding message
     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public ApiResponse putOneArticle(@PathVariable Long id, @RequestBody Article newArticle) {
//        if (this.articleDAO.getArticleByID(newArticle.getArtikelId()).isEmpty()) {
//            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Article:" + newArticle.getArtikelId() + " does not exist!");
//        }
//        Article currentArticle = articleDAO.getArticleByID(id).get();
//        Long currentID = currentArticle.getArtikelId();
//        currentArticle = newArticle;
//        currentArticle.setArtikelId(currentID);
//        this.articleDAO.saveToDatabase(currentArticle);
//        return new ApiResponse(HttpStatus.ACCEPTED, "You updated article " + currentID + "!");
//    }
}
