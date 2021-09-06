package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Override
    List<Order> findAll();

    Order findOrderByIdAndOrderIsEmpty(Long id);

    Optional<Order> getOrderById(Long id);

    @Transactional
    Optional<Order> deleteOrderById(Long id);

}
