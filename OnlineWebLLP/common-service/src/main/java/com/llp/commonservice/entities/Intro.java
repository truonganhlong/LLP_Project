package com.llp.commonservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Intro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imageLink;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "introMapId", nullable = false)
    @JsonIgnore
    private IntroMap introMap;
}
