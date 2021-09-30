package kg.marketplace.easyshop.controller;


import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/add-one")
    @PreAuthorize("hasAuthority('ADD_CATEGORY')")
    public ResponseStatusDTO addCategory(@RequestBody CategoryDTO categoryDTO){
       return  categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/get-one/{id}")
    public CategoryDTO getOneById(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @GetMapping("/get-all")
    public List<CategoryDTO> GetAll(){
       return categoryService.getAllCategories();
    }


    @DeleteMapping("delete-one/{id}")
    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    public ResponseStatusDTO deleteOneById(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/change-one/{id}")
    @PreAuthorize("hasAuthority('UPDATE_CATEGORY')")
    public ResponseStatusDTO changeCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return categoryService.changeCategory(categoryDTO, id);
    }

}
