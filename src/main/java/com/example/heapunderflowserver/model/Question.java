package com.example.heapunderflowserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Question extends Post {
    @NotBlank
    @Size(min = 4, max = 128)
    String title;
    Boolean hasAccepterAnswer = false;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "question_tags",
            joinColumns = {
                    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false),
            }
    )
    List<Tag> tags;
}
