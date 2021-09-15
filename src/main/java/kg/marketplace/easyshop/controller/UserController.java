package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ResponseStatusDTO save(@RequestBody RequestNewUser requestNewUser) {
        return userService.save(requestNewUser);
    }

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
    public ResponseStatusDTO deleteOne(@PathVariable Long id) {
        return userService.deleteOneById(id);
    }

    @PutMapping("/change-user-role")
    public ResponseStatusDTO changeUserRoleById(@RequestBody ChangeUserRoleDTO changeUserRoleDTO) {
        return userService.changeUserRoleById(changeUserRoleDTO);
    }
}
