package kg.marketplace.easyshop.dao;


import kg.marketplace.easyshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "" +
            "SELECT SUM(c.total_sum) " +
            "FROM tb_orders WHERE date " +
            "BETWEEN " +
            "CURRENT_DATE " +
            "AND" +
            "DATEADD('month', -1, GETDATE())", nativeQuery = true)
    Optional<BigDecimal> getPastMonthExpenses();


    @Transactional
    Optional<Order> deleteOrderById(Long id);

    List<Order> findAllByTotalSumLessThan(Double limit);
//    List<Order> findAllByQuantityLessThan20AndTotalSumBetween50And500();
    Order findOrderByIdAndProductIsNull(Long id);
    Optional<Order> getOrderById(Long id);

}
