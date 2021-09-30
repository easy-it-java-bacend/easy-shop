package kg.marketplace.easyshop.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private final ResponseStatusDTO responseStatusDTO;



}
