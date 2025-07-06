package com.ilyaproject.smart_menu_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ilyaproject.smart_menu_server.model.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyDTO {
    @JsonProperty("meals")
    private List<MealDTO> meals;
}
