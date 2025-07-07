package com.ilyaproject.smart_menu_server.utils;

import com.ilyaproject.smart_menu_server.dto.menu.json.*;
import com.ilyaproject.smart_menu_server.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityToDTO {

    public RecipesDTO mapRecipesEntityToDTO(Recipes entity) {
        List<MenuDTO> menuDTOs = entity.getMenu().stream()
                .map(this::mapMenuEntityToDTO)
                .toList();
        return RecipesDTO
                .builder()
                .menu(menuDTOs)
                .build();
    }

    private MenuDTO mapMenuEntityToDTO(Menu menu) {
        List<DailyDTO> dailyDTOs = menu.getDaily().stream()
                .map(this::mapDailyEntityToDTO)
                .toList();
        return MenuDTO
                .builder()
                .daily(dailyDTOs)
                .build();
    }

    private DailyDTO mapDailyEntityToDTO(Daily daily) {
        List<MealDTO> mealDTOs = daily.getMeals().stream()
                .map(this::mapMealEntityToDTO)
                .toList();
        return DailyDTO
                .builder()
                .meals(mealDTOs)
                .build();
    }

    private MealDTO mapMealEntityToDTO(Meal meal) {
        NutritionalInformationDTO info = NutritionalInformationDTO
                .builder()
                .fats(meal.getNutritionalInformation().getFats())
                .carbohydrates(meal.getNutritionalInformation().getCarbohydrates())
                .calories(meal.getNutritionalInformation().getCalories())
                .proteins(meal.getNutritionalInformation().getProteins())
                .build();
        return MealDTO
                .builder()
                .name(meal.getName())
                .ingredients(meal.getIngredients())
                .recipeText(meal.getRecipeText())
                .nutritionalInformation(info)
                .build();

    }
}
