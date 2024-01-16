package com.llp.courseservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    @JsonIgnore
    private Category category;
    @OneToMany(mappedBy = "subCategory")
    private List<Topic> topics;
}
