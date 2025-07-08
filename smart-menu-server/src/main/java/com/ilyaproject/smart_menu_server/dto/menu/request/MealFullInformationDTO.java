package com.ilyaproject.smart_menu_server.dto.menu.request;

import com.ilyaproject.smart_menu_server.model.NutritionalInformation;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealFullInformationDTO {
    private String name;
    private String recipeText;
    private List<String> ingredients;
    private String calories;
    private String proteins;
    private String fats;
    private String carbohydrates;
}
