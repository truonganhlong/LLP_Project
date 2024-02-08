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
@AllArgsConstructor
@NoArgsConstructor
public class CourseTopicKey implements Serializable {
    //@Convert(converter = UUIDConverter.class)
    @Column(name = "courseId", columnDefinition = "VARCHAR(50)")
    private String courseId;
    @Column(name = "topicId")
    private Long topicId;
    public UUID getCourseId() {
        return courseId != null ? UUID.fromString(courseId) : null;
    }
    public void setCourseId(UUID courseId) {
        this.courseId = courseId != null ? courseId.toString() : null;
    }
    public Long getTopicId(){
        return topicId;
    }
    public void setTopicId(Long topicId){
        this.topicId = topicId;
    }
}
