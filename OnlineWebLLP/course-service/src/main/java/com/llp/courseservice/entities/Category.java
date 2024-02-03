package com.llp.courseservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageLink;
    private boolean status;
    @OneToMany(mappedBy = "category")
    private List<Article> articles;
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;
}
