package com.example.shiro;

import com.example.shiro.util.CASUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class GetawayApplicationTests {

	@Test
	void contextLoads() {
		ResponseEntity<String> casToken = CASUtil.getCasToken("2017000031", "oYUUjw7-6q9bbjwRiJY84v9wK6ZA");
		System.out.println(casToken.getStatusCode());
		System.out.println(casToken.getStatusCodeValue());
		System.out.println(casToken.getBody());

	}

}
