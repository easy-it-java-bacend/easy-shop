package kg.marketplace.easyshop.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.marketplace.easyshop.dto.TestDTO;
import kg.marketplace.easyshop.service.MainService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MainServiceImpl implements MainService {
    @Override
    public TestDTO getOne(String url) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addPathSegment("1");

        String urlOk = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(urlOk)
                .build();
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();

        ObjectMapper objectMapper = new ObjectMapper();
        String value = response.body().string();
        TestDTO testDTO = objectMapper.readValue(value, TestDTO.class);
        return testDTO;
    }
}
