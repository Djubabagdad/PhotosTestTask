package com.testtask.demo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.testtask.demo.cache.Cache;
import com.testtask.demo.instance.PhotoDetails;
import com.testtask.demo.instance.PhotoResponse;
import com.testtask.demo.instance.SingleObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.testtask.demo.util.Constants.*;

@Component
public class PhotoFetcher {

    private final Logger logger = LoggerFactory.getLogger(PhotoFetcher.class.getName());



    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = SingleObjectMapper.getInstance();

    public void fetchAll(Integer page) {
        try {
            HttpRequest request = getRequest(PHOTOS_URL + page);

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            PhotoResponse photoResponse = objectMapper.readValue(response.body(), PhotoResponse.class);
            fetchPhotoDetails(photoResponse);

            if (photoResponse.getHasMore()) {
                fetchAll(page + 1);
            }
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public PhotoDetails fetchExact(String id) {
        HttpRequest request = getRequest(PHOTO_DETAILS_URL + id);
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue(response.body(), PhotoDetails.class);
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
            return new PhotoDetails();
        }
    }

    private void fetchPhotoDetails(PhotoResponse photoResponse) {
        photoResponse.getPictures().forEach(photo -> {
            PhotoDetails details = fetchExact(photo.getId());
            photo.setDetails(details);
        });
        Cache.cache.asMap().put(photoResponse.getPage(), photoResponse);
    }

    private HttpRequest getRequest(String url) {
        return HttpRequest.newBuilder()
                .header(CONTENT_TYPE, JSON)
                .header(AUTHORIZATION, TokenFetcher.token.get(TOKEN))
                .uri(URI.create(url))
                .build();
    }
}
