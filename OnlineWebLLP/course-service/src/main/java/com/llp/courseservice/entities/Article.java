package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    @JsonIgnore
    private Category category;
    @OneToMany(mappedBy = "article")
    private List<FaqTopic> faqTopics;
}
