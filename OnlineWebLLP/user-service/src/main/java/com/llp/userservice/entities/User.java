package com.llp.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName())));
        userRoles.forEach(System.out::println);
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
