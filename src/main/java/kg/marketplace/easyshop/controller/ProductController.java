package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get-one-by-id/{id}")
    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        return productService.getOneById(id);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('READ_PRODUCT')")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete-product-by-id/{id}")
    @PreAuthorize("hasAuthority(DELETE_PRODUCT)")
    public ResponseEntity<?> deleteOneById(@PathVariable Long id) {
        return productService.deleteOneById(id);
    }

    @PostMapping("/add-one-product")
    @PreAuthorize("hasAuthority(ADD_PRODUCT)")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

}
