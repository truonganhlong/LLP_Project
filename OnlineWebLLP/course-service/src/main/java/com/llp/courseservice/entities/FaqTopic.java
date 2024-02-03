package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "faqTopic")
public class FaqTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "topicId", nullable = false)
    @JsonIgnore
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "articleId")
    @JsonIgnore
    private Article article;
}
