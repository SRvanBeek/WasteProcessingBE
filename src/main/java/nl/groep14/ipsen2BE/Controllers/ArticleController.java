package nl.groep14.ipsen2BE.Controllers;


import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.Exceptions.OrderNotFoundException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(value = "api/article")
public class ArticleController {

    private final ArticleDAO articleDAO;

    public ArticleController(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postArticle(@RequestBody Article article){
        this.articleDAO.saveToDatabase(article);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Article>> getAllArticles(){
        ArrayList<Article> articles = this.articleDAO.getAll();
        return new ApiResponse(HttpStatus.ACCEPTED, articles);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneArticle(@PathVariable Long id){
        return new ApiResponse(HttpStatus.ACCEPTED, this.articleDAO.getOrderByID(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putOneArticle(@PathVariable Long id,@RequestBody Article newArticle){
        Article currentArticle = articleDAO.getOrderByID(id).get();
        Long currentID = currentArticle.getArtikelId();
        currentArticle = newArticle;
        currentArticle.setArtikelId(currentID);
        this.articleDAO.saveToDatabase(currentArticle);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated article"+currentID+"!");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> orderNotFound(
            OrderNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
