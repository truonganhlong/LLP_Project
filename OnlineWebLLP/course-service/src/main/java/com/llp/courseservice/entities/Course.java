package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String overview;
    private String forWho;
    private String requirement;
    private String target;
    private String imageLink;
    private double rating;
    private int ratingNum;
    private int saleNum;
    private double price;
    private int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Long createdBy;
    private Long updatedBy;
    private boolean isProminent;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false)
    @JsonIgnore
    private Level level;
    @ManyToOne
    @JoinColumn(name = "languageId", nullable = false)
    @JsonIgnore
    private Language language;
    @OneToMany(mappedBy = "course")
    private List<CourseTopic> courseTopics;
    @OneToMany(mappedBy = "course")
    private List<CourseTag> courseTags;
    @OneToMany(mappedBy = "course")
    private List<Review> reviews;
    @OneToMany(mappedBy = "course")
    private List<Section> sections;
    @OneToMany(mappedBy = "course")
    private List<Discount> discounts;
    @OneToMany(mappedBy = "course")
    private List<LastViewCourse> lastViewCourses;
    @OneToMany(mappedBy = "course")
    private List<VisitCourse> visitCourses;
}
