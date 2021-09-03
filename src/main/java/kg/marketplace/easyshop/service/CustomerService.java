package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.CustomerDTO;
import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.enums.Status;

import java.util.List;

public interface CustomerService {

    Customer getOneCustomerById(Long id);
    List<Customer> getAllCustomers();
    void save(CustomerDTO customerDTO);
    Status deleteOneById(Long id);
}
