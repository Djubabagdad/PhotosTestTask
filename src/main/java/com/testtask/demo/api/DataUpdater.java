package com.testtask.demo.api;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataUpdater {

    private FetcherFacade fetcherFacade;

    public DataUpdater(FetcherFacade fetcherFacade) {
        this.fetcherFacade = fetcherFacade;
    }

    @Scheduled(cron = "* */1 * * * *")
    public void reload() {
        fetcherFacade.initData();
    }
}
