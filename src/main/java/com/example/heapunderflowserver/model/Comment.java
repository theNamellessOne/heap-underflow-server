package com.example.heapunderflowserver.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Comment extends Post {
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "question_id", nullable = false)
    Question question;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "answer_id", nullable = false)
    Answer answer;
}
