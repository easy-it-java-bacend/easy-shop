package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<List<Category>> findAllByDeletedFalseAndArchivedFalse();
    Optional<Category> findByIdAndDeletedFalseAndArchivedFalse(Long id);
    Optional<Category> findByName(String name);
}
