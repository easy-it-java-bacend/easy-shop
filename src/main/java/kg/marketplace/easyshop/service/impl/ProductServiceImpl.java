package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.ProductRepository;
import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.dto.ResponseStatusWithObjectDTO;
import kg.marketplace.easyshop.entity.Product;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.ProductNotFoundException;
import kg.marketplace.easyshop.mapper.ProductMapper;
import kg.marketplace.easyshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO getOneById(Long id) {
        return ProductMapper
                .INSTANCE
                .toDTO(
                        productRepository.findProductByIdAndDeletedFalseAndArchivedFalse(id)
                                .orElseThrow
                                        (() -> new ProductNotFoundException("Product with id " + id + " not found")));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return ProductMapper
                .INSTANCE
                .toDTOList(productRepository.findAllByDeletedFalseAndArchivedFalse()
                        .orElseThrow(
                                () -> new ProductNotFoundException("No any products available")));
    }

    @Override
    public ResponseStatusDTO deleteOneById(Long id) {
        return productRepository
                .findProductByIdAndDeletedFalseAndArchivedFalse(id)
                .map(product -> {
                    if (product.isDeleted()) {
                        return new ResponseStatusDTO(Status.FAIL,
                                "Product with id = " + id + " is already deleted");
                    }
                    product.setDeleted(true);
                    productRepository.save(product);
                    return new ResponseStatusWithObjectDTO(Status.SUCCESS, "Product with id = " + id + " is deleted", product);
                }).orElseThrow(() -> new ProductNotFoundException("For id = " + id));
    }

    @Override
    public ResponseStatusDTO addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productRepository.save(product);
        return new ResponseStatusWithObjectDTO(Status.SUCCESS, "Product is added", product);
    }
}
