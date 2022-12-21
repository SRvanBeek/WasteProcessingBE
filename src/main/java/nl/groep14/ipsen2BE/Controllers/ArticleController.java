package nl.groep14.ipsen2BE.Controllers;


import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


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
    public ApiResponse<String> postArticle(@RequestBody Article article) {
        this.articleDAO.saveToDatabase(article);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You added an article!");
    }

    /**
     * getAllArticles gets all Article entities in the database.
     *
     * @return an ApiResponse which has a list of Article entities as payload.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Article>> getAllArticles() {
        List<Article> articles = this.articleDAO.getAll();
        return new ApiResponse<>(HttpStatus.ACCEPTED, articles);
    }

    /**
     * getOneArticle gets one specific Article entity based on the id if one exists
     *
     * @param id is the articlenumber of the Article entity.
     * @return ApiResponse with the selected article as payload.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<Article> getOneArticle(@PathVariable String id) {
        Optional<Article> article = this.articleDAO.getArticleByArtikelNummer(id);
        return article.map(value -> new ApiResponse<>(HttpStatus.ACCEPTED, value)).orElseGet(() -> new ApiResponse<>(HttpStatus.NOT_FOUND, "article does not exist"));
    }
}
