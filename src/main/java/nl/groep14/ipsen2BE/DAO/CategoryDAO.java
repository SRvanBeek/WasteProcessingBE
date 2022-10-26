package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CategoryDAO {
    private final CategoryRepository categoryRepository;

    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveToDatabase(Category category){
        this.categoryRepository.save(category);
    }

    public ArrayList<Category> getAll(){
        return (ArrayList<Category>) this.categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByID(Long id){
        return this.categoryRepository.findById(id);
    }
}
