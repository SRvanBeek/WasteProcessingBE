package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderDAO orderDAO;
    private final CutWasteDAO cutWasteDAO;
    private final ArticleDAO articleDAO;

    public OrderService(OrderDAO orderDAO, CutWasteDAO cutWasteDAO, ArticleDAO articleDAO) {
        this.orderDAO = orderDAO;
        this.cutWasteDAO = cutWasteDAO;
        this.articleDAO = articleDAO;
    }

    public ApiResponse OrderToArticle(Long Id){
        Order order = orderDAO.getOrderByID(Id).get();
        Cutwaste cutwaste = cutWasteDAO.getByID(order.getCutwasteID()).get();
        Article article = articleDAO.getArticleByArtikelNummer(cutwaste.getArtikelnummer()).get();
        return new ApiResponse(HttpStatus.ACCEPTED, article);

    }

}
