package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.courseservice.entities.keys.ReviewKey;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "review")
public class Review {
    @EmbeddedId
    private ReviewKey id;
    private String content;
    private int rating;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private boolean isProminent;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId", nullable = false)
    @JsonIgnore
    private Course course;

}
