package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> save(@RequestBody RequestNewUser requestNewUser) {
        return userService.save(requestNewUser);
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return userService.getOneUserById(id);
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        return userService.deleteOneById(id);
    }

    @PutMapping("/change-user-role")
    public ResponseEntity<?> changeUserRoleById(@RequestBody ChangeUserRoleDTO changeUserRoleDTO) {
        return userService.changeUserRoleById(changeUserRoleDTO);
    }


}
