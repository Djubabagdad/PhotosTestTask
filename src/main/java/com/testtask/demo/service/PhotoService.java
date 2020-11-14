package com.testtask.demo.service;

import com.testtask.demo.instance.Photo;
import com.testtask.demo.instance.PhotoDetails;
import com.testtask.demo.instance.PhotoResponse;

import java.util.List;

public interface PhotoService {

    PhotoResponse findAll(Integer page);

    PhotoDetails find(String id);

    List<Photo> search(String searchTerm);
}
