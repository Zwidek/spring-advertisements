package pl.pb.ogloszeniadrobne.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pb.ogloszeniadrobne.model.Category;
import pl.pb.ogloszeniadrobne.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

}
