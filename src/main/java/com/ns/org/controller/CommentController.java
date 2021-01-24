package com.ns.org.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ns.org.service.CommentService;

@RestController
public class CommentController {

	private static final Logger log = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@PostMapping("comment")
	public String comment(@RequestParam("articleId") Long articleId, @RequestParam("content") String content) {
		try {
			for (int i = 0; i < 100; i++) {
				int j = i;
				new Thread(() -> {
					commentService.postComment(articleId, content + j);
				}).start();
				;
			}

		} catch (Exception e) {
			log.error("{}", e);
			return "error: " + e.getMessage();
		}
		return "success";
	}
}
