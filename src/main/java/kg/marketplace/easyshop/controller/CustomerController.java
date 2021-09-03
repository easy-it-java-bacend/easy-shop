package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.CustomerDTO;
import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return Status.SUCCESS;
    }

    @GetMapping("/get-one/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getOne(@PathVariable Long id) {
        return customerService.getOneCustomerById(id);
    }

    @GetMapping("/get-all")
    public List<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/delete/{id}")
    public Status deleteOne(@PathVariable Long id) {
        return customerService.deleteOneById(id);
    }
}
