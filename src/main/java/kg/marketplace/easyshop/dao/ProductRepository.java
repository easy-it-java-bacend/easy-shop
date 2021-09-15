package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    Optional<List<Product>> findAllByDeletedFalseAndArchivedFalse();
    Optional<Product> findProductByIdAndDeletedFalseAndArchivedFalse(Long id);
}
