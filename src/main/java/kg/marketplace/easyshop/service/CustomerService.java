package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dao.CustomerRepository;
import kg.marketplace.easyshop.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
