package com.ilyaproject.smart_menu_server;

import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.email.generator.html.RecipeHtmlGenerator;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.EntityToDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RecipeHtmlGeneratorIntegrationTest {
    @Autowired
    private EntityToDTO etd;

    @Autowired
    private RecipeHtmlGenerator recipeHtmlGenerator;

    @Autowired
    private UserRepository repository; // or your service that loads RecipesDTO

    @Test
    @Transactional
    void shouldGenerateHtmlForRecipeFromDb() {
        // Given
        User user = repository.findById(1) // or use a service method
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        RecipesDTO recipe = etd.mapRecipesEntityToDTO(user.getRecipes());

        // When
        List<String> htmlList = recipeHtmlGenerator.generateListOfRecipesHtml(recipe);

        htmlList.forEach(System.out::println);

        // Then
        assertFalse(htmlList.isEmpty());

    }
}
