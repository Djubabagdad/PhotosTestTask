package com.testtask.demo.controller;

import com.testtask.demo.instance.Photo;
import com.testtask.demo.instance.PhotoDetails;
import com.testtask.demo.instance.PhotoResponse;
import com.testtask.demo.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = "/images")
    public ResponseEntity<PhotoResponse> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page) {
        var data = photoService.findAll(page);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/images/{id}")
    public ResponseEntity<PhotoDetails> getAll(@PathVariable String id) {
        var data = photoService.find(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{searchTerm}")
    public ResponseEntity<List<Photo>> search(@PathVariable String searchTerm) {
        var data = photoService.search(searchTerm);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
