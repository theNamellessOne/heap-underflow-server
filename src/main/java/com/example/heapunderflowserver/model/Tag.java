package com.example.heapunderflowserver.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Tag extends JpaEntity {
    @NotBlank
    @Size(min = 1, max = 16)
    String name;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "tags")
    List<Question> questions;
}
