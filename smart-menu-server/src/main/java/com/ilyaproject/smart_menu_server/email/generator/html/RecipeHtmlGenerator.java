    package com.ilyaproject.smart_menu_server.email.generator.html;

    import com.ilyaproject.smart_menu_server.dto.menu.json.MealDTO;
    import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class RecipeHtmlGenerator {
        private final HtmlProvider provider;
        public List<String> generateListOfRecipesHtml(RecipesDTO recipes){
            return recipes.getMenu()
                    .stream()
                    .flatMap(menu -> menu.getDaily().stream())
                    .flatMap(daily -> daily.getMeals().stream())
                    .map(this::convertMealInHtmlPage)
                    .collect(Collectors.toList());
        }

        private String convertMealInHtmlPage(MealDTO meal){
            return provider.getHtml(meal.getName(), meal.getIngredients(), meal.getRecipeText(),
                    meal.getNutritionalInformation().getCalories(), meal.getNutritionalInformation().getProteins(),
                    meal.getNutritionalInformation().getFats(), meal.getNutritionalInformation().getCarbohydrates());
        }
    }
