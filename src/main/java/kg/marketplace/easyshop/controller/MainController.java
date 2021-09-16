package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.TestDTO;
import kg.marketplace.easyshop.service.MainService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/involve-another-service")
    public TestDTO getResult(@RequestBody String url) throws IOException {
        return mainService.getOne(url);
    }
}
