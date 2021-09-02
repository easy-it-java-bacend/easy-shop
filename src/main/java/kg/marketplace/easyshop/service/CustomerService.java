package kg.marketplace.easyshop.service;

import exceptions.CustomerNotFoundException;
import exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.CustomerRepository;
import kg.marketplace.easyshop.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    public void save(Customer customer) {
        if (customer.getEmail().isEmpty()) {
            throw new CustomerSaveException("Empty required email field");
        }
        if (customer.getDob() == null) {
            throw new CustomerSaveException("Empty required dob field");
        }
        int age = (int) ((customer.getDob().getTime()) / 365.25 - 1970);
        if (age < 18) {
            throw new CustomerSaveException("Customer is not adult enought");
        }
        customerRepository.save(customer);
    }

    public Customer getOneCustomerById(Long id) {
        return customerRepository.getById(id);
    }
}
