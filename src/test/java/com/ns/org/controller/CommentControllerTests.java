package com.ns.org.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ns.org.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void concurrentComment() {
		String url = "http://localhost:8080/comment";
		for (int i = 0; i < 100; i++) {
			int count = i;
			new Thread(() -> {
				MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
				params.add("articleId", "1");
				params.add("content", " test content " + count);
				String result = testRestTemplate.postForObject(url, params, String.class);
			}).start();
		}

	}
}
