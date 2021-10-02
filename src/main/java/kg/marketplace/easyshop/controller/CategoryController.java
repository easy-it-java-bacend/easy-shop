package kg.marketplace.easyshop.controller;


import kg.marketplace.easyshop.dto.CategoryDTO;
import kg.marketplace.easyshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO){
       return  categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> GetAll(){
       return categoryService.getAllCategories();
    }


    @DeleteMapping("delete-one/{id}")
    @PreAuthorize("hasAuthority('DELETE_CATEGORY')")
    public ResponseEntity<?> deleteOneById(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/change-one/{id}")
    @PreAuthorize("hasAuthority('UPDATE_CATEGORY')")
    public ResponseEntity<?> changeCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return categoryService.changeCategory(categoryDTO, id);
    }

}
