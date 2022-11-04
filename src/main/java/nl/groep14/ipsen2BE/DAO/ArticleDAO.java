package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * OrderDAO is the DAO of the Order entity
 * @author Dino Yang
 */
@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;

    public ArticleDAO(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    /**
     * Saves a single Article to the database.
     * @param article the Waste that is to be saved
     */
    public void saveToDatabase(Article article){
        this.articleRepository.save(article);
    }
    /**
     * gets all Article in the database.
     * @return arrayList of Article
     */
    public ArrayList<Article> getAll(){
        return (ArrayList<Article>) this.articleRepository.findAll();
    }
    /**
     * Attempts to return a single Article if one exists in the database with the given id.
     * @param id The id that is used to find a specific Article.
     * @return an Article if an Article with the id exists.
     */
    public Optional<Article> getArticleByID(Long id){
        return this.articleRepository.findById(id);
    }

    /**
     * picks a random Article that has no user ID.
     * @return Article
     */
    public Article getRandomArticle() {
        long qty = articleRepository.count();
        Random rand = new Random();
        long id = rand.nextLong(qty);
        Article chosenArticle = getArticleByID(id).get();
        while(chosenArticle.getUserId() != null){
            id = rand.nextLong(qty);
            chosenArticle = getArticleByID(id).get();
        }
        return chosenArticle;
    }


}
