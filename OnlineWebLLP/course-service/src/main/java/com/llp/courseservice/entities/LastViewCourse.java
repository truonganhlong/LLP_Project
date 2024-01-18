package com.llp.courseservice.entities;

import com.llp.courseservice.entities.keys.LastViewCourseKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "lastViewCourse")
public class LastViewCourse {
    @EmbeddedId
    private LastViewCourseKey id;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;
}
