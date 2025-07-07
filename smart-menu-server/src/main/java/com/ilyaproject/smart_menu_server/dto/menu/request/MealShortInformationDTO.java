package com.ilyaproject.smart_menu_server.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealShortInformationDTO {
    private Integer id;
    private String name;
    private String calories;
}
