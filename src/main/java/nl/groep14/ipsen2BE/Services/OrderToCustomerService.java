package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Leftover;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
@Service
public class OrderToCustomerService {
        private final OrderDAO orderDAO;
        private final LeftoverDAO leftoverDAO;
        private final ArticleDAO articleDAO;
        private final CustomerDAO customerDAO;

        public OrderToCustomerService(OrderDAO orderDAO, LeftoverDAO leftoverDAO, ArticleDAO articleDAO, CustomerDAO customerDAO) {
            this.orderDAO = orderDAO;
            this.leftoverDAO = leftoverDAO;
            this.articleDAO = articleDAO;
            this.customerDAO = customerDAO;
        }

        public ApiResponse getOrderToCustomer(Long Id) {
            Order order = orderDAO.getOrderByID(Id).get();
            Leftover leftover = leftoverDAO.getById(order.getLeftoverId());
            Article article = articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer()).get();

            return new ApiResponse(HttpStatus.ACCEPTED, customerDAO.getCustomerByID(article.getLeverancier()));
        }
    }