package com.example.heapunderflowserver.repository;

import com.example.heapunderflowserver.model.JpaEntity;

public interface JpaRepository<T extends JpaEntity> extends org.springframework.data.jpa.repository.JpaRepository<T, Integer> {
}
