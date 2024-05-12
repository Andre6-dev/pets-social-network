package com.devandre.petsnetwork.repository;

import com.devandre.petsnetwork.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}