package com.ilyaproject.smart_menu_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("recipeText")
    private String recipeText;
    @JsonProperty("ingredients")
    private List<String> ingredients;
    @JsonProperty("nutritionalInformation")
    private NutritionalInformationDTO nutritionalInformation;
}
