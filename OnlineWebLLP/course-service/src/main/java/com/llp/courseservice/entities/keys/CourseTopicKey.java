package com.llp.courseservice.entities.keys;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTopicKey implements Serializable {
    @Column(name = "courseId")
    private UUID courseId;
    @Column(name = "topicId")
    private Long topicId;
}
