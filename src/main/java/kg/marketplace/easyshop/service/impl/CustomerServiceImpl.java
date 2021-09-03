package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dto.CustomerDTO;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.CustomerRepository;
import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.mapper.CustomerMapper;
import kg.marketplace.easyshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public void save(CustomerDTO customerDTO) {
        if (customerDTO.getEmail().isEmpty()) {
            throw new CustomerSaveException("Empty required email field");
        }
        if (customerDTO.getDob() == null) {
            throw new CustomerSaveException("Empty required dob field");
        }
        int age = (int) ((customerDTO.getDob().getTime()) / 365.25 - 1970);
        if (age < 18) {
            throw new CustomerSaveException("Customer is not adult enought");
        }
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDTO);
        customerRepository.save(customer);
    }

    public Customer getOneCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("For id : " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Status deleteOneById(Long id) {
        if (customerRepository.deleteCustomerById(id).isPresent()) {
            customerRepository.deleteCustomerById(id);
            return Status.SUCCESS;
        }
        return Status.FAIL;
    }
}
