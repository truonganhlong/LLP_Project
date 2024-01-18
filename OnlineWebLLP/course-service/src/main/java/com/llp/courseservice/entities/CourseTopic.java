package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.courseservice.entities.keys.CourseTopicKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "courseTopic")
public class CourseTopic {
    @EmbeddedId
    private CourseTopicKey id;
    @ManyToOne
    @MapsId("topicId")
    @JoinColumn(name = "topicId", nullable = false)
    @JsonIgnore
    private Topic topic;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId", nullable = false)
    @JsonIgnore
    private Course course;
}
