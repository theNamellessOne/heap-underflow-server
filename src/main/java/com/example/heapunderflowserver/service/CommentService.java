package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.Comment;
import com.example.heapunderflowserver.repository.CommentRepository;
import com.example.heapunderflowserver.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends JpaService<Comment> {
    public CommentService(JpaRepository<Comment> repository) {
        super(repository);
    }

    public Page<Comment> findByQuestionId(Integer id, Pageable pageable) {
        return ((CommentRepository) repository).findCommentsByQuestionId(id, pageable);
    }

    public Page<Comment> findByAnswerId(Integer id, Pageable pageable) {
        return ((CommentRepository) repository).findCommentsByAnswerId(id, pageable);
    }

    public Page<Comment> findByAuthorId(Integer id, Pageable pageable) {
        return ((CommentRepository) repository).findCommentsByAuthorId(id, pageable);
    }
}
