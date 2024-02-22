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
public class LastViewCourseKey implements Serializable {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "courseId", columnDefinition = "VARCHAR(50)")
    private String courseId;

    public UUID getCourseId() {
        return courseId != null ? UUID.fromString(courseId) : null;
    }
    public void setCourseId(UUID courseId) {
        this.courseId = courseId != null ? courseId.toString() : null;
    }
    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
}
