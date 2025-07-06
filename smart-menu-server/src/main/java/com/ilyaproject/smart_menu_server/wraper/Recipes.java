package com.ilyaproject.smart_menu_server.wraper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ilyaproject.smart_menu_server.dto.MenuDTO;
import com.ilyaproject.smart_menu_server.model.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipes {
    @JsonProperty("menu")
    private List<MenuDTO> menu;
}