package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        return Status.SUCCESS;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/get-one/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getOne(@PathVariable Long id) {
        return userService.getOneCustomerById(id);
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllCustomers();
    }

    @DeleteMapping("/delete/{id}")
    public Status deleteOne(@PathVariable Long id) {
        return userService.deleteOneById(id);
    }
}
