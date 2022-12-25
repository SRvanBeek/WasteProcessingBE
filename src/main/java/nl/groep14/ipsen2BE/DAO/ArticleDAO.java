package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Leftover;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * OrderDAO is the DAO of the Order entity
 * @author Dino Yang
 */
@Component
public class ArticleDAO {

    private final ArticleRepository articleRepository;
    private final LeftoverDAO leftoverDAO;

    public ArticleDAO(ArticleRepository articleRepository, LeftoverDAO cutwaste) {
        this.articleRepository = articleRepository;
        this.leftoverDAO = cutwaste;
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
    public Optional<Article> getArticleByID(long id){
        return this.articleRepository.findById(id);
    }
    
        /**
     * attempts to return a single article on the given articleNumber.
     * @param id the articlenumber which will be used to retrieve the Article from the database.
     * @return a single Article if one exists with the given articleNumber.
     */
    public Optional<Article> getArticleByArtikelNummer(String id){
        return this.articleRepository.getArticleByArtikelnummer(id);
    }

    /**
     * picks a random Article that has no user ID.
     * @return Article
     */
    public Article getRandomArticle() {
        ArrayList<Article> articles = this.getAll();
        return articles.get(new Random().nextInt(articles.size()) - 1);
    }
}
