package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.Question;
import com.example.heapunderflowserver.repository.JpaRepository;
import com.example.heapunderflowserver.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends JpaService<Question> {
    public QuestionService(JpaRepository<Question> repository) {
        super(repository);
    }

    public Page<Question> findByAuthorId(Integer id, Pageable pageable) {
        return ((QuestionRepository) repository).findQuestionsByAuthorId(id, pageable);
    }

    public Page<Question> findByTagsId(Integer id, Pageable pageable) {
        return ((QuestionRepository) repository).findQuestionsByTagsId(id, pageable);
    }
}
