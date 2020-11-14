package com.testtask.demo.service.impl;

import com.testtask.demo.api.PhotoFetcher;
import com.testtask.demo.cache.Cache;
import com.testtask.demo.instance.Photo;
import com.testtask.demo.instance.PhotoDetails;
import com.testtask.demo.instance.PhotoResponse;
import com.testtask.demo.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final Logger logger = LoggerFactory.getLogger(PhotoFetcher.class.getName());

    @Override
    public PhotoResponse findAll(Integer page) {
        return Cache.cache.getIfPresent(page);
    }

    @Override
    public PhotoDetails find(String id) {
        try {
            List<Integer> list = getPageNumbers();

            Map<Integer, PhotoResponse> map = Cache.cache.getAllPresent(list);
            PhotoDetails response = new PhotoDetails();

            for (PhotoResponse photoResponse : map.values()) {

                Optional<Photo> instance = photoResponse.getPictures()
                        .stream()
                        .filter(photo -> photo.getDetails().getId().equals(id))
                        .findAny();

                if (instance.isPresent()) {
                    response = instance.get().getDetails();
                    break;
                }
            }

            return response;
        } catch (ExecutionException e) {
            logger.error(e.getMessage());
            return new PhotoDetails();
        }
    }

    @Override
    public List<Photo> search(String searchTerm) {
        try {
            List<Integer> size = getPageNumbers();

            Map<Integer, PhotoResponse> map = Cache.cache.getAllPresent(size);
            List<Photo> responseList = new ArrayList<>();

            for (PhotoResponse photoResponse : map.values()) {
                List<Photo> list = photoResponse.getPictures().stream()
                        .parallel()
                        .filter(photo -> {
                            String full = photo.getDetails().getAuthor() + " " +
                                    photo.getDetails().getCamera() + " " +
                                    photo.getDetails().getTags() + " " +
                                    photo.getDetails().getId() + " " +
                                    photo.getDetails().getCroppedPicture() + " " +
                                    photo.getDetails().getFullPicture();

                            return full.contains(searchTerm);
                        }).collect(Collectors.toList());
                responseList.addAll(list);
            }
            return responseList;

        } catch (ExecutionException e) {
            logger.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Integer> getPageNumbers() throws ExecutionException {
        Integer size = Cache.cache.get(1).getPageCount();
        return IntStream.range(1, size).boxed().collect(Collectors.toList());
    }
}
