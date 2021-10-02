package kg.marketplace.easyshop.service;


import kg.marketplace.easyshop.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {


    ResponseEntity<?> addCategory(CategoryDTO categoryDTO);
    ResponseEntity<?> deleteCategory(Long id);
    ResponseEntity<?> changeCategory(CategoryDTO categoryDTO, Long id);
    ResponseEntity<?> getCategory(Long id);
    ResponseEntity<?> getAllCategories();
}
