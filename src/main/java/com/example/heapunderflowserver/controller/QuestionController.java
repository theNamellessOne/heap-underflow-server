package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.Answer;
import com.example.heapunderflowserver.model.Question;
import com.example.heapunderflowserver.model.User;
import com.example.heapunderflowserver.service.AnswerService;
import com.example.heapunderflowserver.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("questions")
public class QuestionController extends AbstractController<Question> {
    private final AnswerService answerService;

    public QuestionController(QuestionService service, AnswerService answerService) {
        super(service);
        this.answerService = answerService;
    }

    @GetMapping("author/{id}")
    public Page<Question> findByAuthorId(@PathVariable(name = "id") Integer authorId,
                                         Pageable pageable) {
        return ((QuestionService) service).findByAuthorId(authorId, pageable);
    }

    @GetMapping("tag/{id}")
    public Page<Question> findByTagsId(@PathVariable(name = "id") Integer tagId,
                                       Pageable pageable) {
        return ((QuestionService) service).findByTagsId(tagId, pageable);
    }

    @PatchMapping("{questionId}/accept-answer/{answerId}")
    public void acceptAnswer(@PathVariable(name = "questionId") Integer questionId,
                             @PathVariable(name = "answerId") Integer answerId) {
        toggleAnswerAcceptance(questionId, answerId, true);
    }

    @PatchMapping("{questionId}/disaccept-answer/{answerId}")
    public void disacceptAnswer(@PathVariable(name = "questionId") Integer questionId,
                                @PathVariable(name = "answerId") Integer answerId) {
        toggleAnswerAcceptance(questionId, answerId, false);
    }

    private void toggleAnswerAcceptance(Integer questionId, Integer answerId, boolean value) {
        Question question = service.findOne(questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity with specified id was not found"));
        Answer answer = answerService.findOne(answerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity with specified id was not found"));

        question.setHasAccepterAnswer(value);
        answer.setIsAcceptedAnswer(value);

        service.save(question);
        answerService.save(answer);
    }

    @Override
    boolean canModify(Integer entityId) {
        Optional<Question> entity = service.findOne(entityId);
        var currentUser = ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());

        return entity.isPresent() && Objects.equals(entity.get().getAuthor().getId(), currentUser.getId());
    }
}
