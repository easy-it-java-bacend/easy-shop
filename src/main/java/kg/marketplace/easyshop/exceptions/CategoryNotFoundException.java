package kg.marketplace.easyshop.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryNotFoundException extends RuntimeException{

   private final ResponseStatusDTO responseStatusDTO;
}
