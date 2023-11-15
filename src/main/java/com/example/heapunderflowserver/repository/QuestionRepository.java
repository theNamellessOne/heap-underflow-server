package com.example.heapunderflowserver.repository;

import com.example.heapunderflowserver.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepository extends JpaRepository<Question> {
    Page<Question> findQuestionsByAuthorId(Integer id, Pageable pageable);

    Page<Question> findQuestionsByTagsId(Integer id, Pageable pageable);
}
