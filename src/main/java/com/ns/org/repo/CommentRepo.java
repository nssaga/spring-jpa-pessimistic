package com.ns.org.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ns.org.entity.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

}
