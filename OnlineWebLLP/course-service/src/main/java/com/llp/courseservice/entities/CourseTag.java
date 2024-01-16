package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.courseservice.entities.keys.CourseTagKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseTag {
    @EmbeddedId
    private CourseTagKey id;
    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tagId", nullable = false)
    @JsonIgnore
    private Tag tag;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId", nullable = false)
    @JsonIgnore
    private Course course;
}
