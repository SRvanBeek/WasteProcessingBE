package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CategoryControllerTest {
    @Mock
    private CategoryDAO categoryDAO;

    @BeforeEach
    void setup(){
        categoryDAO = mock(CategoryDAO.class);
    }

    @Test
    public void should_return404StatusCode_when_categoryToUpdateDoesNotExist(){
        //Arrange
        Category categoryUnderTesting = new Category();
        categoryUnderTesting.setId(1);

        CategoryController categoryController = new CategoryController(this.categoryDAO);
        when(this.categoryDAO.getCategoryByID(categoryUnderTesting.getId())).thenReturn(Optional.empty()); //STUBBING

        ApiResponse expectedResult = new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");

        //Act
        ApiResponse actualResult = categoryController.updateCategory(categoryUnderTesting);

        //Assert
        assertEquals(expectedResult.getCode(), actualResult.getCode());
        verify(this.categoryDAO, times(1)).getCategoryByID(categoryUnderTesting.getId());
    }

}