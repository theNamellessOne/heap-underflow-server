package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.JpaEntity;
import com.example.heapunderflowserver.repository.JpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class JpaService<T extends JpaEntity> {
    final JpaRepository<T> repository;

    public Optional<T> findOne(Integer id) {
        return repository.findById(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
