package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.CustomerRepository;
import kg.marketplace.easyshop.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("For id : " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

}
