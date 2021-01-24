package com.ns.org.repo;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ns.org.entity.Article;

@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {

	@Query(value = "select * from article a where a.id = :id FOR UPDATE", nativeQuery = true)
	Optional<Article> findArticleForUpdate(@Param("id") Long id);

	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({ @QueryHint(name = "javax.persistence.lock.timeout", value = "1000") })
	@Query("select a from Article a where a.id = :id")
	Optional<Article> findArticleWithPessimisticLock(@Param("id") Long id);

}
