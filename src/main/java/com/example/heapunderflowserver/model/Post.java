package com.example.heapunderflowserver.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class Post extends JpaEntity {
    @NotBlank
    @Size(min = 32, max = 1024)
    String body;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    User author;
}
