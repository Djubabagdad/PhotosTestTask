package com.testtask.demo.cache;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.testtask.demo.api.PhotoFetcher;
import com.testtask.demo.instance.PhotoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache {
    private static final Logger logger = LoggerFactory.getLogger(PhotoFetcher.class.getName());

    public static CacheLoader<Integer, PhotoResponse> loader = new CacheLoader<>() {
        @Override
        public PhotoResponse load(Integer key) {
            try {
                return loader.load(key);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return new PhotoResponse();
            }
        }
    };

    public static LoadingCache<Integer, PhotoResponse> cache;
}
