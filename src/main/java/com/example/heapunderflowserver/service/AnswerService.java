package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.Answer;
import com.example.heapunderflowserver.repository.AnswerRepository;
import com.example.heapunderflowserver.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends JpaService<Answer> {
    public AnswerService(JpaRepository<Answer> repository) {
        super(repository);
    }

    public Page<Answer> findByQuestionId(Integer id, Pageable pageable) {
        return ((AnswerRepository) repository).findAnswersByQuestionId(id, pageable);
    }

    public Page<Answer> findByAuthorId(Integer id, Pageable pageable) {
        return ((AnswerRepository) repository).findAnswersByAuthorId(id, pageable);
    }
}
