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

    public void saveToDatabase(Article article){
        this.articleRepository.save(article);
    }

    public ArrayList<Article> getAll(){
        return (ArrayList<Article>) this.articleRepository.findAll();
    }

    public Optional<Article> getArticleByID(Long id){
        return this.articleRepository.findById(id);
    }

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
