package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.JpaEntity;
import com.example.heapunderflowserver.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractController<T extends JpaEntity> {
    final JpaService<T> service;

    @GetMapping("/")
    public Page<T> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PatchMapping("/rating-increase/{id}")
    public void increaseRating(@PathVariable(name = "id") Integer id) {
        changeRating(id, 1);
    }

    @PatchMapping("/rating-decrease/{id}")
    public void decreaseRating(@PathVariable(name = "id") Integer id) {
        changeRating(id, -1);
    }

    protected void changeRating(Integer entityId, Integer amount) {
        T entity = service.findOne(entityId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity with specified id was not found"));
        entity.setRating(entity.getRating() + amount);
        service.save(entity);
    }

    @GetMapping("/{id}")
    public Optional<T> findOne(@PathVariable(name = "id") Integer id) {
        return service.findOne(id);
    }

    @PostMapping("/")
    public T save(@RequestBody T entity) throws AccessDeniedException {
        if (entity.getId() != null && !canModify(entity.getId())) {
            throw new AccessDeniedException("Access Denied");
        }

        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) throws AccessDeniedException {
        if (!canModify(id)) {
            throw new AccessDeniedException("Access Denied");
        }

        service.delete(id);
    }

    abstract boolean canModify(Integer entityId);
}
