package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Models.Leftover;

public class CustomerService {
    CustomerDAO customerDAO;
    ArticleDAO articleDAO;
    LeftoverDAO leftoverDAO;

    public CustomerService(CustomerDAO customerDAO, ArticleDAO articleDAO, LeftoverDAO leftoverDAO) {
        this.customerDAO = customerDAO;
        this.articleDAO = articleDAO;
        this.leftoverDAO = leftoverDAO;
    }

//    public ApiResponse getLeftoverByCustomerId(String id){
//        Article article = articleDAO.getArticleByCustomerId(id);
//        Leftover leftover = leftoverDAO.
//
//    }
}
