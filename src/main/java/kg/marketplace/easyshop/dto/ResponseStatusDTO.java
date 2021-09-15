package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStatusDTO<E> {
    private Status status;
    private String description;
    private E targetObject;
}
