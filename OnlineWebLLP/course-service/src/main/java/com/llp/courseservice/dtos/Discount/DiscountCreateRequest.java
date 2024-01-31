package com.llp.courseservice.dtos.Discount;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DiscountCreateRequest {
    @JsonProperty(required = true)
    private int discountValue;
    @JsonProperty(required = true)
    private String startTime;
    @JsonProperty(required = true)
    private String endTime;
    @JsonProperty(required = true)
    private UUID courseId;
}
