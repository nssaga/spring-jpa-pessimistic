package com.ns.org.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ns.org.entity.Article;
import com.ns.org.entity.Comment;
import com.ns.org.repo.ArticleRepo;
import com.ns.org.repo.CommentRepo;

@Service
public class CommentService {
	@Autowired
	private ArticleRepo articleRepository;

	@Autowired
	private CommentRepo commentRepository;

	@Transactional
	public void postComment(Long articleId, String content) {
		try {
			Optional<Article> articleOptional = articleRepository.findArticleForUpdate(articleId);
			if (!articleOptional.isPresent()) {
				throw new RuntimeException("no corresponding article");
			}
			Article article = articleOptional.get();

			Comment comment = new Comment();
			comment.setArticleId(articleId);
			comment.setContent(content);
			commentRepository.save(comment);

			article.setCommentCount(article.getCommentCount() + 1);
			articleRepository.save(article);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
