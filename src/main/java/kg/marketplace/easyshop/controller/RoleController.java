package kg.marketplace.easyshop.controller;

import io.swagger.annotations.Api;
import kg.marketplace.easyshop.dto.RoleDTO;
import kg.marketplace.easyshop.enums.Permissions;
import kg.marketplace.easyshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
@Api("Controller for Roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {
        return roleService.addRole(roleDTO);
    }

    @GetMapping("/get-all-permissions")
    public Permissions[] getPermissions() {
        return Permissions.values();
    }

}
