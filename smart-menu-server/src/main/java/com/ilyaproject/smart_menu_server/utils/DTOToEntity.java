package com.ilyaproject.smart_menu_server.utils;

import com.ilyaproject.smart_menu_server.dto.DailyDTO;
import com.ilyaproject.smart_menu_server.dto.MealDTO;
import com.ilyaproject.smart_menu_server.dto.MenuDTO;
import com.ilyaproject.smart_menu_server.dto.RecipesDTO;
import com.ilyaproject.smart_menu_server.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTOToEntity {
    public Recipes mapRecipesDTOToEntity(RecipesDTO dto){
        List<Menu> menu = dto.getMenu().stream()
                .map(this::mapMenuDTOToEntity)
                .toList();
        return Recipes
                .builder()
                .menu(menu)
                .build();
    }
    private Menu mapMenuDTOToEntity(MenuDTO menuDTO){
        List<Daily> dailies = menuDTO.getDaily().stream()
                .map(this::mapDailyDTOToEntity)
                .toList();
        return Menu
                .builder()
                .daily(dailies)
                .build();
    }
    private Daily mapDailyDTOToEntity(DailyDTO dailyDTO){
        List<Meal> meals = dailyDTO.getMeals().stream()
                .map(this::mapMealDTOToEntity)
                .toList();
        return Daily
                .builder()
                .meals(meals)
                .build();
    }
    private Meal mapMealDTOToEntity(MealDTO mealDTO){
        NutritionalInformation information = NutritionalInformation
                .builder()
                .calories(mealDTO.getNutritionalInformation().getCalories())
                .carbohydrates(mealDTO.getNutritionalInformation().getCarbohydrates())
                .fats(mealDTO.getNutritionalInformation().getFats())
                .proteins(mealDTO.getNutritionalInformation().getProteins())
                .build();
        return Meal
                .builder()
                .name(mealDTO.getName())
                .ingredients(mealDTO.getIngredients())
                .recipeText(mealDTO.getRecipeText())
                .nutritionalInformation(information)
                .build();
    }
}
