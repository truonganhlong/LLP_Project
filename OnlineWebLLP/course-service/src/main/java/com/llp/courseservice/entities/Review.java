package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.courseservice.entities.keys.ReviewKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @EmbeddedId
    private ReviewKey id;
    private String content;
    private int rating;
    private boolean isProminent;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId", nullable = false)
    @JsonIgnore
    private Course course;

}
