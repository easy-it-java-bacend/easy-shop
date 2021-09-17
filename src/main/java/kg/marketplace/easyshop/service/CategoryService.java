package kg.marketplace.easyshop.service;


import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {


    ResponseStatusDTO addCategory(CategoryDTO categoryDTO);
    ResponseStatusDTO deleteCategory(Long id);
    ResponseStatusDTO changeCategory(CategoryDTO categoryDTO, Long id);
    CategoryDTO getCategory(Long id);
    List<CategoryDTO> getAllCategories();
}
