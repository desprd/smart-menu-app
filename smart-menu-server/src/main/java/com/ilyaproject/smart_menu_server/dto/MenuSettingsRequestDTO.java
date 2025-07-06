package com.ilyaproject.smart_menu_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MenuSettingsRequestDTO {
    private Boolean isVegetarian;
    private Boolean isGlutenFree;
    private Boolean isDairyFree;
    private Boolean isNutFree;
    @NotBlank
    private String cuisine;
    private String excluded;
}
