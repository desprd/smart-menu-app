package com.ilyaproject.smart_menu_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NutritionalInformation {
    private String calories;
    private String proteins;
    private String fats;
    private String carbohydrates;
}
