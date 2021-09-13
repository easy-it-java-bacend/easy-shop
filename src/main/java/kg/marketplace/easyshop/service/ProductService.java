package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.enums.Status;

import java.util.List;

public interface ProductService {

    ProductDTO getOneById(Long id);
    List<ProductDTO> getAllProducts();
    Status deleteOneById(Long id);

    Status addProduct(ProductDTO productDTO);
}
