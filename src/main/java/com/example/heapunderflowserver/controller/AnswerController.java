package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.Answer;
import com.example.heapunderflowserver.model.User;
import com.example.heapunderflowserver.service.AnswerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("answers")
public class AnswerController extends AbstractController<Answer> {
    public AnswerController(AnswerService service) {
        super(service);
    }

    @GetMapping("author/{id}")
    public Page<Answer> findByAuthorId(@PathVariable(name = "id") Integer authorId, Pageable pageable) {
        return ((AnswerService) service).findByAuthorId(authorId, pageable);
    }

    @GetMapping("question/{id}")
    public Page<Answer> findByQuestionId(@PathVariable(name = "id") Integer questionId, Pageable pageable) {
        return ((AnswerService) service).findByAuthorId(questionId, pageable);
    }

    @Override
    boolean canModify(Integer entityId) {
        Optional<Answer> entity = service.findOne(entityId);
        var currentUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return entity.isPresent() && Objects.equals(entity.get().getAuthor().getId(), currentUser.getId());
    }
}
