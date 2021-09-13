package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.ProductRepository;
import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.entity.Product;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.ProductNotFoundException;
import kg.marketplace.easyshop.mapper.ProductMapper;
import kg.marketplace.easyshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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
    public Status deleteOneById(Long id) {
        Optional<Product> p = productRepository
                .findProductByIdAndDeletedFalseAndArchivedFalse(id)
                .map(product -> {
                    product.setDeleted(true);
                    return productRepository.save(product);
                });
        return p.isPresent() ? Status.SUCCESS : Status.FAIL;
    }

    @Override
    public Status addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productRepository.save(product);
        return Status.SUCCESS;
    }
}
