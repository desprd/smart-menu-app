package com.ilyaproject.smart_menu_server.dto.menu.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuPageDTO {
    private List<MealShortInformationDTO> mealsShortInformation;
}
