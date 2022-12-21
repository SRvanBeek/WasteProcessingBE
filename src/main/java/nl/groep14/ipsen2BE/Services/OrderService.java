package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Leftover;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderDAO orderDAO;
    private final LeftoverDAO leftoverDAO;
    private final ArticleDAO articleDAO;

    public OrderService(OrderDAO orderDAO, LeftoverDAO leftoverDAO, ArticleDAO articleDAO) {
        this.orderDAO = orderDAO;
        this.leftoverDAO = leftoverDAO;
        this.articleDAO = articleDAO;
    }

    public ApiResponse OrderToArticle(Long Id){
        Order order = orderDAO.getOrderByID(Id).get();
        Leftover leftover = leftoverDAO.getById(order.getLeftoverId());
        articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer());
        return new ApiResponse(HttpStatus.ACCEPTED, articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer()));

    }

}
