package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.User;
import com.example.heapunderflowserver.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController extends AbstractController<User> {
    public UserController(UserService service) {
        super(service);
    }

    @Override
    boolean canModify(Integer entityId) {
        Optional<User> entity = service.findOne(entityId);
        var currentUser = ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());

        return entity.isPresent() && Objects.equals(entity.get().getId(), currentUser.getId());
    }
}
