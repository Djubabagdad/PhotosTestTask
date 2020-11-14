package com.testtask.demo;

import com.testtask.demo.api.TokenFetcher;
import com.testtask.demo.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		FetcherFacade facade = new FetcherFacade(fetcher);
		facade.initializeData();
	}

	@Test
	void getToken() {
		Assert.notNull(TokenFetcher.token.get(Constants.TOKEN), "Token is null");
	}

}
