package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody Customer customer) {
        customerServiceImpl.save(customer);
        return Status.SUCCESS;
    }

    @GetMapping("/get-one/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getOne(@PathVariable Long id) {
        return customerServiceImpl.getOneCustomerById(id);
    }

    @GetMapping("/get-all")
    public List<Customer> getAll() {
        return customerServiceImpl.getAllCustomers();
    }

    @DeleteMapping("/delete/{id}")
    public Status deleteOne(@PathVariable Long id) {
        return customerServiceImpl.deleteOneById(id);
    }
}
