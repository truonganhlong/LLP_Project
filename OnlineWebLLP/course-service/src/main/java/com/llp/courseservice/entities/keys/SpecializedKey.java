package com.llp.courseservice.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpecializedKey implements Serializable {
    @Column(name = "userId")
    private Long userId;
    @Column(name = "categoryId")
    private Long categoryId;
    @Column(name = "subCategoryId")
    private Long subCategoryId;
}
