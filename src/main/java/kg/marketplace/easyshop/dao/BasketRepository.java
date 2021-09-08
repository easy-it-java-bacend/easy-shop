package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Override
    List<Basket> findAll();
    Basket findBasketById(Long id);
    Optional<Basket> getBasketById(Long id);
    @Transactional
    Optional<Basket> deleteBasketById(Long id);
}
