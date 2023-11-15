package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.Comment;
import com.example.heapunderflowserver.model.User;
import com.example.heapunderflowserver.service.CommentService;
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
@RequestMapping("comments")
public class CommentController extends AbstractController<Comment> {
    public CommentController(CommentService service) {
        super(service);
    }


    @GetMapping("question/{id}")
    public Page<Comment> findByQuestionId(@PathVariable(name = "id") Integer id, Pageable pageable) {
        return ((CommentService) service).findByQuestionId(id, pageable);
    }

    @GetMapping("answer/{id}")
    public Page<Comment> findByAnswerId(@PathVariable(name = "id") Integer id, Pageable pageable) {
        return ((CommentService) service).findByAnswerId(id, pageable);
    }

    @GetMapping("author/{id}")
    public Page<Comment> findByAuthorId(@PathVariable(name = "id") Integer id, Pageable pageable) {
        return ((CommentService) service).findByAuthorId(id, pageable);
    }

    @Override
    boolean canModify(Integer entityId) {
        Optional<Comment> entity = service.findOne(entityId);
        var currentUser = ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());

        return entity.isPresent() && Objects.equals(entity.get().getAuthor().getId(), currentUser.getId());
    }
}
