package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.enums.Status;
import lombok.Data;

@Data
public class ResponseStatusWithObjectDTO<E> extends ResponseStatusDTO {

    private E targetObject;

    public ResponseStatusWithObjectDTO(Status status, String description, E targetObject) {
        super(status, description);
        this.targetObject = targetObject;
    }
}
