package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.CategoryRepository;
import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.dto.ResponseStatusWithObjectDTO;
import kg.marketplace.easyshop.entity.Category;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CategoryNotFoundException;
import kg.marketplace.easyshop.exceptions.CategorySaveException;
import kg.marketplace.easyshop.mapper.CategoryMapper;
import kg.marketplace.easyshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public ResponseStatusDTO addCategory(CategoryDTO categoryDTO) {

        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        if (categoryDTO.getName().isEmpty()) throw new CategorySaveException(new ResponseStatusDTO(Status.EXCEPTION,"Name field must be filled"));

        categoryRepository.save(category);
        return new ResponseStatusWithObjectDTO<>(Status.SUCCESS, "Category added", category);
    }

    @Override
    public ResponseStatusDTO deleteCategory(Long id) {
     Category category = categoryRepository.findById(id)
             .orElseThrow(()-> new CategoryNotFoundException(new ResponseStatusDTO(Status.EXCEPTION,"Category by id"+ id+ "wasn't found")));
     if (category.isDeleted())
         return new ResponseStatusDTO(Status.FAIL,"Category by id"+ id+ "is already deleted");
        category.setDeleted(true);

        categoryRepository.save(category);
        return new ResponseStatusWithObjectDTO<>(Status.SUCCESS, "Product with id = " + id + " is deleted", category);

    }

    @Override
    public ResponseStatusDTO changeCategory(CategoryDTO categoryDTO, Long id) {


       return  categoryRepository.findById(id)
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    categoryRepository.save(category);
                    return new  ResponseStatusWithObjectDTO<>(Status.SUCCESS,
                            "Category by id: "+id+" has changed", category);
                })
                .orElseThrow(()-> new CategoryNotFoundException(new ResponseStatusDTO(Status.EXCEPTION,"Category wasn't found for id"+ id)));
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        return CategoryMapper.INSTANCE.toDTO(categoryRepository.findById(id)
                .orElseThrow(()->new CategoryNotFoundException(new ResponseStatusDTO(Status.EXCEPTION,"Category wasn't found for id"+ id))));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.INSTANCE.toDTOList(categoryRepository.findAllByDeletedFalseAndArchivedFalse()
                .orElseThrow(()->new CategoryNotFoundException(
                        new ResponseStatusDTO(Status.EXCEPTION,
                                "Categories weren't found"))));
    }
}
