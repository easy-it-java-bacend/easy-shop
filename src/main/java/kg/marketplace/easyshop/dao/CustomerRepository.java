package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    List<Customer> findAll();

    Customer findCustomerByIdAndBasketIsNull(Long id);
    //Select 1 from tb_customers Where basket IS NULL;

    Optional<Customer> getCustomerById(Long id);

    @Transactional
    Optional<Customer> deleteCustomerById(Long id);
}

