package com.ilyaproject.smart_menu_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalInformationDTO {
    @JsonProperty("calories")
    private String calories;
    @JsonProperty("proteins")
    private String proteins;
    @JsonProperty("fats")
    private String fats;
    @JsonProperty("carbohydrates")
    private String carbohydrates;
}
