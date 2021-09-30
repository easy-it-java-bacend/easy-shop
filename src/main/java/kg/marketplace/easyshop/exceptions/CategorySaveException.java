package kg.marketplace.easyshop.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategorySaveException extends RuntimeException{

  private final ResponseStatusDTO responseStatusDTO;

}
