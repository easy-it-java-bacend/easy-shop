package kg.marketplace.easyshop.dao;

import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "UPDATE tb_customers SET role = ?2 WHERE id = ?1", nativeQuery = true)
    void changeRoleById(Long id, Role role);

//    @Query(value = "SELECT c FROM tb_customer c WHERE date_part('year', age(dob)) > 18")
//    Optional<Customer> findAdultCustomers();



    @Transactional
    Optional<User> deleteCustomerById(Long id);
}

