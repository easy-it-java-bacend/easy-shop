package kg.marketplace.easyshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
