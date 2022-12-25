package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Controllers.ArticleController;
import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class SnijServiceTest {
    SnijService snijService;
    @Mock
    WasteService wasteService;
    @Mock
    ArticleDAO articleDAO;
    @Mock
    CustomerDAO customerDAO;
    @Mock
    CategoryDAO categoryDAO;
    @Mock
    VoorraadDAO voorraadDAO;
    @Mock
    OrderDAO orderDAO;
    @Mock
    LeftoverDAO leftoverDAO;

    @BeforeEach
    void setup() {
        snijService = new SnijService(articleDAO, customerDAO, orderDAO, leftoverDAO, voorraadDAO, wasteService, categoryDAO);
    }

    @Test
    void should_Return_TheExpectedWeight_When_TheArticleWeightAndWidth_And_TheMetrageAreGiven() {
        //arrange
        Article article = new Article();
        article.setGewicht(315);
        article.setStofbreedte(155);

        double metrage = 40;
        double expectedWeight = 8129.03;

        //act
        double actualWeight = snijService.calculateWeight(article, metrage);

        //assert
        assertEquals(expectedWeight, actualWeight);
    }
}