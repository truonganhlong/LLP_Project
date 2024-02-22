package com.llp.courseservice.entities.keys;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CourseTagKey implements Serializable {
    @Column(name = "courseId", columnDefinition = "VARCHAR(50)")
    private String courseId;
    @Column(name = "tagId")
    private Long tagId;

    public UUID getCourseId() {
        return courseId != null ? UUID.fromString(courseId) : null;
    }
    public void setCourseId(UUID courseId) {
        this.courseId = courseId != null ? courseId.toString() : null;
    }
    public Long getTagId(){
        return tagId;
    }
    public void setTagId(Long tagId){
        this.tagId = tagId;
    }
}
