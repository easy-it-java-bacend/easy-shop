package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.CategoryRepository;
import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.entity.Category;
import kg.marketplace.easyshop.mapper.CategoryMapper;
import kg.marketplace.easyshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        return   categoryRepository.findByName(category.getName())
                .map(p->{
                    log.error("Category already exists by name"+ category.getName());
                    return ResponseEntity.unprocessableEntity().build();
                }).orElseGet(()->{
                    categoryRepository.save(category);
                    return ResponseEntity.ok().build();});
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        return categoryRepository
                .findByIdAndDeletedFalseAndArchivedFalse(id)
                .map(category -> {
                    if (category.isDeleted()) {
                        return ResponseEntity.unprocessableEntity().build();
                    }
                    category.setDeleted(true);
                    categoryRepository.save(category);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElseGet(()->{
                    log.error("Category was not found by id" + id);
                    return ResponseEntity.ok().build();
                });
    }

    @Override
    public ResponseEntity<?> changeCategory(CategoryDTO categoryDTO, Long id) {


        return categoryRepository.findByName(categoryDTO.getName())
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    categoryRepository.save(category);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("Category not found for name=" + categoryDTO.getName());
                    return ResponseEntity.ok().build();
                });
    }

    @Override
    public ResponseEntity<?> getCategory(Long id) {
        return categoryRepository.findById(id).map(category ->
                ResponseEntity.ok().body(CategoryMapper.INSTANCE.toDTO(category)))
                .orElseGet(()-> {
                    log.error("Category not found by id" + id);
                    return ResponseEntity.ok().build();
                });
    }

    @Override
    public ResponseEntity<?> getAllCategories() {
        return categoryRepository.findAllByDeletedFalseAndArchivedFalse()
                .map(categories -> ResponseEntity.ok()
                        .body(CategoryMapper.INSTANCE.toDTOList(categories)))
                .orElseGet(()->{
                    log.error("Categories not found by id" );
                    return ResponseEntity.ok().build();
                });
    }
}
