package com.testtask.demo.api;

import com.google.common.cache.CacheBuilder;
import com.testtask.demo.cache.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FetcherFacade {

    @Value("${cache_size}")
    private Integer cacheSize;

    private final TokenFetcher tokenFetcher;
    private final PhotoFetcher photoFetcher;

    public FetcherFacade(TokenFetcher tokenFetcher, PhotoFetcher photoFetcher) {
        this.tokenFetcher = tokenFetcher;
        this.photoFetcher = photoFetcher;

    }

    @PostConstruct
    public void initData() {
        Cache.cache = CacheBuilder.newBuilder().maximumSize(cacheSize).build(Cache.loader);

        tokenFetcher.fetch();
        photoFetcher.fetchAll(1);
    }
}
