package com.llp.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String headline;
    private String biography;
    private String twitterLink;
    private String facebookLink;
    private String linkedinLink;
    private String youtubeLink;
    private String imageLink;
    private int otp;
    private boolean verified;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate participateDay;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    private List<UserNotification> userNotifications;
    @OneToMany(mappedBy = "userReceive")
    private List<Message> messagesReceive;
    @OneToMany(mappedBy = "userSend")
    private List<Message> messagesSend;
    @OneToMany(mappedBy = "user")
    private List<WishlistAndCart> wishlistAndCarts;
    @OneToMany(mappedBy = "user")
    private List<YourCourse> yourCourses;
    @OneToMany(mappedBy = "user")
    private List<YourLecture> yourLectures;
    @OneToMany(mappedBy = "user")
    private List<RegisterInstructorForm> registerInstructorForms;
}
