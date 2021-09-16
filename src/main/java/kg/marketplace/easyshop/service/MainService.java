package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.TestDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface MainService {

    TestDTO getOne(String url) throws IOException;

}
