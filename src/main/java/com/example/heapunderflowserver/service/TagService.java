package com.example.heapunderflowserver.service;

import com.example.heapunderflowserver.model.Tag;
import com.example.heapunderflowserver.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService extends JpaService<Tag> {
    public TagService(TagRepository repository) {
        super(repository);
    }
}
