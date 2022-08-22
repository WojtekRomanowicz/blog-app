package com.example.blogapp.repository;

import com.example.blogapp.model.jpa.Comment;
import com.example.blogapp.model.jpa.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

}