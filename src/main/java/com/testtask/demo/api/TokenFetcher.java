package com.testtask.demo.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.demo.instance.SingleObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static com.testtask.demo.util.Constants.*;

@Component
@PropertySource("classpath:config.properties")
public class TokenFetcher {

    @Value("${api_key}")
    private String apiKey;

    public static Map<String, String> token = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(PhotoFetcher.class.getName());

    public void fetch() {
        try {
            Map<String, String> values = new HashMap<>() {{
                put(API_KEY, apiKey);
            }};

            ObjectMapper objectMapper = SingleObjectMapper.getInstance();
            String requestBody = objectMapper
                    .writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header(CONTENT_TYPE, JSON)
                    .uri(URI.create(AUTH_URL))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            token = objectMapper.readValue(response.body(), new TypeReference<>() {});
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
