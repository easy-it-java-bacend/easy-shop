package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.ProductRepository;
import kg.marketplace.easyshop.dto.ProductDTO;
import kg.marketplace.easyshop.entity.Product;
import kg.marketplace.easyshop.mapper.ProductMapper;
import kg.marketplace.easyshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<?> getOneById(Long id) {
        return productRepository
                .findProductByIdAndDeletedFalseAndArchivedFalse(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK)
                .body(ProductMapper.INSTANCE.toDTO(product)))
                .orElseGet(()->{
                    log.error("Product was not found by id" );
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        return productRepository
                .findAllByDeletedFalseAndArchivedFalse()
                .map(products -> ResponseEntity.status(HttpStatus.OK)
                .body(ProductMapper.INSTANCE.toDTOList(products)))
                        .orElseGet(()->{
                            log.error("Product was not found by id" );
                            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                        });
    }

    @Override
    public ResponseEntity<?> deleteOneById(Long id) {
        return productRepository
                .findProductByIdAndDeletedFalseAndArchivedFalse(id)
                .map(product -> {
                    if (product.isDeleted()) {
                        return ResponseEntity.unprocessableEntity().build();
                    }
                    product.setDeleted(true);
                    productRepository.save(product);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElseGet(()->{
                    log.error("Product was not found by id" + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    @Override
    public ResponseEntity<?> addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
      return   productRepository.findByName(productDTO.getName())
                .map(p->{
                    log.error("Product already exists by name"+ productDTO.getName());
                    return ResponseEntity.unprocessableEntity().build();
                }).orElseGet(()->{
                  productRepository.save(product);
                return ResponseEntity.notFound().build();});
    }
}
