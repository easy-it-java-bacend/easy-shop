package kg.marketplace.easyshop.exceptions;

import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategorySaveException extends RuntimeException{

  private final ResponseStatusDTO responseStatusDTO;

}
