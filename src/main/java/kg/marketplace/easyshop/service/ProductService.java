package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> getOneById(Long id);
    ResponseEntity<?> getAllProducts();
    ResponseEntity<?> deleteOneById(Long id);

    ResponseEntity<?> addProduct(ProductDTO productDTO);
}
