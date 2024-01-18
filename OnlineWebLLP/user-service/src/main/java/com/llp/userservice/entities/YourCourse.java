package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.YourCourseKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "yourCourse")
public class YourCourse {
    @EmbeddedId
    private YourCourseKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
}
