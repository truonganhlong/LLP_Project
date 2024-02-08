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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTagKey implements Serializable {
    @Column(name = "courseId", columnDefinition = "VARCHAR(50)")
    private UUID courseId;
    @Column(name = "tagId")
    private Long tagId;
}
