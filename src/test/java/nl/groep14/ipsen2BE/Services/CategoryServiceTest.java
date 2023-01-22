package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Exceptions.CategoryOutOfBoundsException;
import nl.groep14.ipsen2BE.Exceptions.CategoryOverlapException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.CategoryJson;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CategoryServiceTest {
    private CategoryService categoryService;
    @Mock
    private CategoryDAO categoryDAO;

    private ArrayList<Category> categories;

    @BeforeEach
    void setup() {
        this.categoryDAO = mock(CategoryDAO.class);
        this.categoryService = new CategoryService(categoryDAO);

        this.categories = new ArrayList<>();
        Collections.addAll(categories,
                new Category(1, "A1", "100% PES || 100% PL", true),
                new Category(2, "A2", ">20% PES && <50% PES || >20% PL && <50% PL", true),
                new Category(3, "A3", ">50% PES && <99% PES || >50% PL && <99% PL", true)
        );
    }

    @Test
    public void should_return404StatusCode_when_categoryToUpdateDoesNotExist(){
        //Arrange
        CategoryJson categoryUnderTesting = new CategoryJson();
        categoryUnderTesting.setId(1);

        CategoryService categoryService = new CategoryService(this.categoryDAO);
        when(this.categoryDAO.getCategoryByID(categoryUnderTesting.getId())).thenReturn(Optional.empty()); //STUBBING

        ApiResponse<Object> expectedResult = new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");

        //Act
        ApiResponse<String> actualResult = categoryService.updateCategory(categoryUnderTesting);

        //Assert
        assertEquals(expectedResult.getCode(), actualResult.getCode());
        verify(this.categoryDAO, times(1)).getCategoryByID(categoryUnderTesting.getId());
    }


    @Test
    void should_returnExpectedHashmap_When_ConditionStringIsGiven() {
        //arrange
        Category category = new Category("A2", ">20% PES && <50% PES || >20% PL && <50% PL", true);

        HashMap<String, ArrayList<String>> expected = new HashMap<>();

        ArrayList<String> arrayPES = new ArrayList<>();
        ArrayList<String> arrayPL = new ArrayList<>();
        Collections.addAll(arrayPES, ">20", "<50");
        Collections.addAll(arrayPL, ">20", "<50");

        expected.put("PES", arrayPES);
        expected.put("PL", arrayPL);

        //act
        HashMap<String, ArrayList<String>> result = this.categoryService.getSeparateConditions(category);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void should_returnExpectedString_when_HashmapIsGiven() {
        //arrange
        HashMap<String, ArrayList<String>> hashMapUnderTesting = new HashMap<>();

        ArrayList<String> arrayVI = new ArrayList<>();
        ArrayList<String> arrayCO = new ArrayList<>();
        Collections.addAll(arrayVI, ">20", "<50");
        Collections.addAll(arrayCO, ">30", "<75");

        hashMapUnderTesting.put("VI", arrayVI);
        hashMapUnderTesting.put("CO", arrayCO);

        String expected = ">20% VI && <50% VI || >30% CO && <75% CO";

        //act
        String result = categoryService.combineSeparateConditions(hashMapUnderTesting);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void should_throwCategoryOverlapException_when_valuesConflictWithExistingCategory() {
        //arrange
        HashMap<String, ArrayList<String>> conditionsUnderTesting = new HashMap<>();

        ArrayList<String> arrayPES = new ArrayList<>();
        ArrayList<String> arrayPL = new ArrayList<>();
        Collections.addAll(arrayPES, ">20", "<50");
        Collections.addAll(arrayPL, ">20", "<60");

        conditionsUnderTesting.put("PES", arrayPES);
        conditionsUnderTesting.put("PL", arrayPL);

        CategoryJson categoryJson = new CategoryJson(3, "A2", conditionsUnderTesting, true);

        Mockito.when(this.categoryDAO.getAll()).thenReturn(categories);

        //assert
        assertThrows(CategoryOverlapException.class, () -> {
            categoryService.noOverlap(categoryJson, false);
        });
        verify(this.categoryDAO, times(1)).getAll();
    }

    @Test
    void should_returnTrue_when_SavingNewCategoryWithoutOverlap() throws CategoryOverlapException {
        //arrange
        HashMap<String, ArrayList<String>> conditionsUnderTesting = new HashMap<>();

        ArrayList<String> arrayCO = new ArrayList<>();
        Collections.addAll(arrayCO, "100");

        conditionsUnderTesting.put("CO", arrayCO);

        CategoryJson categoryJson = new CategoryJson(4, "B1", conditionsUnderTesting, true);

        Mockito.when(this.categoryDAO.getAll()).thenReturn(categories);

        //assert
        assertTrue(categoryService.noOverlap(categoryJson, true));
        verify(this.categoryDAO, times(1)).getAll();
    }

    @Test
    void should_returnTrue_when_RequestMethodIsPut_while_Overlapping() throws CategoryOverlapException {
        //arrange
        HashMap<String, ArrayList<String>> conditionsUnderTesting = new HashMap<>();

        ArrayList<String> arrayPES = new ArrayList<>();
        ArrayList<String> arrayPL = new ArrayList<>();
        Collections.addAll(arrayPES, ">60", "<80");
        Collections.addAll(arrayPL, ">60", "<80");

        conditionsUnderTesting.put("PES", arrayPES);
        conditionsUnderTesting.put("PL", arrayPL);

        CategoryJson categoryJson = new CategoryJson(3, "A2", conditionsUnderTesting, true);

        Mockito.when(this.categoryDAO.getAll()).thenReturn(categories);

        //assert
        assertTrue(categoryService.noOverlap(categoryJson, true));
        verify(this.categoryDAO, times(1)).getAll();
    }

    @Test
    void should_throwCategoryOutOfBoundsException_when_valuesAreAbove100() {
        //arrange
        HashMap<String, ArrayList<String>> conditionsUnderTesting = new HashMap<>();

        ArrayList<String> arrayPES = new ArrayList<>();
        ArrayList<String> arrayPL = new ArrayList<>();
        Collections.addAll(arrayPES, ">20", "<150");
        Collections.addAll(arrayPL, ">20", "<60");

        conditionsUnderTesting.put("PES", arrayPES);
        conditionsUnderTesting.put("PL", arrayPL);

        CategoryJson categoryJson = new CategoryJson(3, "A2", conditionsUnderTesting, true);

        Mockito.when(this.categoryDAO.getAll()).thenReturn(categories);

        //assert
        assertThrows(CategoryOutOfBoundsException.class, () -> {
            categoryService.noOverlap(categoryJson, false);
        });
        verify(this.categoryDAO, times(1)).getAll();
    }

    @Test
    void should_throwCategoryOutOfBoundsException_when_valuesAreBelow0() {
        //arrange
        HashMap<String, ArrayList<String>> conditionsUnderTesting = new HashMap<>();

        ArrayList<String> arrayPES = new ArrayList<>();
        ArrayList<String> arrayPL = new ArrayList<>();
        Collections.addAll(arrayPES, ">-20", "<50");
        Collections.addAll(arrayPL, ">20", "<60");

        conditionsUnderTesting.put("PES", arrayPES);
        conditionsUnderTesting.put("PL", arrayPL);

        CategoryJson categoryJson = new CategoryJson(3, "A2", conditionsUnderTesting, true);

        Mockito.when(this.categoryDAO.getAll()).thenReturn(categories);

        //assert
        assertThrows(CategoryOutOfBoundsException.class, () -> {
            categoryService.noOverlap(categoryJson, false);
        });
        verify(this.categoryDAO, times(1)).getAll();
    }
}