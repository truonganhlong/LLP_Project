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
public class ProminentTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String overview;
    @ManyToOne
    @JoinColumn(name = "topicId", nullable = false)
    @JsonIgnore
    private Topic topic;
}
