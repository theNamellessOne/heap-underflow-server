package com.example.heapunderflowserver.repository;

import com.example.heapunderflowserver.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository extends JpaRepository<Comment> {
    Page<Comment> findCommentsByAnswerId(Integer id, Pageable pageable);

    Page<Comment> findCommentsByAuthorId(Integer id, Pageable pageable);

    Page<Comment> findCommentsByQuestionId(Integer id, Pageable pageable);
}
