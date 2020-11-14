package com.testtask.demo.instance;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SingleObjectMapper {
    private static ObjectMapper objectMapper;

    private SingleObjectMapper() {

    }

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}
