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
    List<Customer> findAllByOrdersIsNull();
    List<Customer> findAllByOrdersIsNotNull();
    Optional<Customer> findCustomerById(Long id);

//    @Query(value = "SELECT c FROM tb_customer c WHERE date_part('year', age(dob)) > 18")
//    Optional<Customer> findAdultCustomers();



    @Transactional
    Optional<Customer> deleteCustomerById(Long id);
}

