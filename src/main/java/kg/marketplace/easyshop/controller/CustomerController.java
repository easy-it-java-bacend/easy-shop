package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dao.CustomerRepository;
import kg.marketplace.easyshop.entity.Basket;
import kg.marketplace.easyshop.entity.Customer;
import kg.marketplace.easyshop.entity.Sex;
import kg.marketplace.easyshop.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @RequestMapping("/save")
    public void save() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Nodir");
        customer.setLastnName("Umarov");
        customer.setDob(new Date());
        customer.setEmail("gmail");
        customer.setSex(Sex.MALE);
        customer.setBasket(new Basket());

        customerService.save(customer);
    }

    @RequestMapping("/print/{id}")
    public void print(@PathVariable Long id) {
        System.out.println(customerService.getOneCustomerById(id));
    }
}
