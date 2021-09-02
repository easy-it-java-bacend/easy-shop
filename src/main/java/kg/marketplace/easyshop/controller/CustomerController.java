package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody Customer customer) {
        customerService.save(customer);
        return Status.SUCCESS;
    }

    @GetMapping("/get-one/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer print(@PathVariable Long id) {
        return customerService.getOneCustomerById(id);
    }

    @GetMapping("/get-all")
    public List<Customer> printAll() {
        return customerService.getAllCustomers();
    }

}
