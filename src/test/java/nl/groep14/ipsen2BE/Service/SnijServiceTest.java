package nl.groep14.ipsen2BE.Service;

import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Services.SnijService;
import nl.groep14.ipsen2BE.Services.WasteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SnijServiceTest {

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
    OrderDAO orderDAO;

    @BeforeEach
    void setUp(){
        articleDAO = mock(ArticleDAO.class);
        customerDAO = mock(CustomerDAO.class);
        categoryDAO = mock(CategoryDAO.class);
        wasteService = mock(WasteService.class);
        orderDAO = mock(OrderDAO.class);
        snijService = new SnijService(articleDAO,customerDAO,wasteService,categoryDAO,orderDAO);
    }

    @Test
    public void should_returnVoorraadString_whenSnijApplication(){
        Article testArticle = new Article();
        testArticle.setArtikelId(1);
        testArticle.setBreedte(500);
        testArticle.setCustomerId(1);
        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setMin_meter(1);
        testCustomer.setMax_meter(2);
        ArrayList<Category> categoryList = new ArrayList<>();
        when(articleDAO.getRandomArticle()).thenReturn(testArticle);
        when(customerDAO.getCustomerByID(anyLong())).thenReturn(Optional.of(testCustomer));
        when(categoryDAO.getAll()).thenReturn(categoryList);
        String expectedString = "Voorraad, 1";

        String response = snijService.snijApplication();

        assertEquals(response,expectedString);
    }

    @Test
    public void should_returnWasteString_whenSnijApplication(){
        Article testArticle = new Article();
        testArticle.setArtikelId(1);
        testArticle.setBreedte(500);
        testArticle.setCustomerId(1);
        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setMin_meter(900);
        testCustomer.setMax_meter(1000);
        ArrayList<Category> categoryList = new ArrayList<>();
        when(articleDAO.getRandomArticle()).thenReturn(testArticle);
        when(customerDAO.getCustomerByID(anyLong())).thenReturn(Optional.of(testCustomer));
        when(categoryDAO.getAll()).thenReturn(categoryList);
        String expectedString = "Waste, 1";

        String response = snijService.snijApplication();

        assertEquals(response,expectedString);
    }

    @Test
    public void should_returnOrderString_whenSnijApplication(){
        Article testArticle = new Article();
        testArticle.setArtikelId(1);
        testArticle.setBreedte(3);
        testArticle.setCustomerId(1);
        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setMin_meter(0);
        testCustomer.setMax_meter(1);
        ArrayList<Category> categoryList = new ArrayList<>();
        when(articleDAO.getRandomArticle()).thenReturn(testArticle);
        when(customerDAO.getCustomerByID(anyLong())).thenReturn(Optional.of(testCustomer));
        when(categoryDAO.getAll()).thenReturn(categoryList);
        String expectedString = "Order, 1";

        String response = snijService.snijApplication();

        assertEquals(response,expectedString);
    }
}
