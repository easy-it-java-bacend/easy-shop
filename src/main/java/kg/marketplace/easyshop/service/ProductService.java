package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getOneById(Long id);
    List<ProductDTO> getAllProducts();
    ResponseStatusDTO deleteOneById(Long id);

    ResponseStatusDTO addProduct(ProductDTO productDTO);
}
