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
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String allAbout;
    private String description;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "subCategoryId", nullable = false)
    @JsonIgnore
    private SubCategory subCategory;
    @OneToMany(mappedBy = "topic")
    private List<CourseTopic> courseTopics;
    @OneToMany(mappedBy = "topic")
    private List<ProminentTopic> prominentTopics;
}
