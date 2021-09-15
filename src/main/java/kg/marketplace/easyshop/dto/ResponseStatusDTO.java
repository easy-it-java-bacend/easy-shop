package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatusDTO {
    private Status status;
    private String description;
}
