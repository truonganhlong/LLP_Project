package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.YourLectureKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class YourLecture {
    @EmbeddedId
    private YourLectureKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
}
