package com.ilyaproject.smart_menu_server.dto.menu.json;

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
public class DailyDTO {
    @JsonProperty("meals")
    private List<MealDTO> meals;
}
