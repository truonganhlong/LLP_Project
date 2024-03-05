package com.llp.courseservice.entities;

import com.llp.courseservice.entities.keys.SpecializedKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "specialized")
public class Specialized {
    @EmbeddedId
    private SpecializedKey id;
}
