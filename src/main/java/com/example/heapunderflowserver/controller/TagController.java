package com.example.heapunderflowserver.controller;

import com.example.heapunderflowserver.model.Tag;
import com.example.heapunderflowserver.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagController extends AbstractController<Tag> {
    public TagController(TagService service) {
        super(service);
    }

    @Override
    boolean canModify(Integer entityId) {
        return false;
    }
}
