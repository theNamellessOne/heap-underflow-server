package com.example.heapunderflowserver.repository;

import com.example.heapunderflowserver.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerRepository extends JpaRepository<Answer> {
    Page<Answer> findAnswersByAuthorId(Integer id, Pageable pageable);

    Page<Answer> findAnswersByQuestionId(Integer id, Pageable pageable);
}
