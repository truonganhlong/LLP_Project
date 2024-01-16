package com.llp.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.llp.userservice.entities.keys.UserRoleKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRole {
    @EmbeddedId
    private UserRoleKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "roleId")
    @JsonIgnore
    private Role role;
}
