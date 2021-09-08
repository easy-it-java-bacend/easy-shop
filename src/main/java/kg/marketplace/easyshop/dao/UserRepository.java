package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();
    List<User> findAllByOrdersIsNull();
    List<User> findAllByOrdersIsNotNull();
    Optional<User> findCustomerById(Long id);

//    @Query(value = "SELECT c FROM tb_customer c WHERE date_part('year', age(dob)) > 18")
//    Optional<Customer> findAdultCustomers();



    @Transactional
    Optional<User> deleteCustomerById(Long id);
}

