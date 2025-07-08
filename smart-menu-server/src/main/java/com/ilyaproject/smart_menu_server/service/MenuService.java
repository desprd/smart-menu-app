package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.ai.MenuGenerator;
import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MealShortInformationDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MenuPageDTO;
import com.ilyaproject.smart_menu_server.exception.GenerationException;
import com.ilyaproject.smart_menu_server.exception.MenuException;
import com.ilyaproject.smart_menu_server.model.Recipes;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.DTOToEntity;
import com.ilyaproject.smart_menu_server.utils.EntityToDTO;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final UserRepository repository;
    private final UserUtils utils;
    private final MenuGenerator generator;
    private final DTOToEntity dte;
    private final EntityToDTO etd;
    @Transactional
    public void initialMenuGeneration(Authentication authentication) throws Exception{
        try {
            User user = utils.getUserByAuthentication(authentication);
            RecipesDTO recipesdto = generator.generate(user).get();
            Recipes recipes = dte.mapRecipesDTOToEntity(recipesdto);
            user.setRecipes(recipes);
            repository.save(user);
        }catch (Exception e){
            log.error("Failed to generate menu", e);
            throw new GenerationException("Failed to generate menu", e);
        }
    }
    public RecipesDTO getUserRecipes(Authentication authentication) throws Exception{
        try {
            User user = utils.getUserByAuthentication(authentication);
            Recipes recipes = user.getRecipes();
            return etd.mapRecipesEntityToDTO(recipes);
        }catch (Exception e){
            log.error("Failed to get menu", e);
            throw new MenuException("Failed to get menu", e);
        }
    }

    @Cacheable(value = "meals", key = "#authentication.name")
    public MenuPageDTO getInformationForMenuPage(Authentication authentication) throws Exception{
        try {
            User user = utils.getUserByAuthentication(authentication);
            List<MealShortInformationDTO> meals = user.getRecipes().getMenu().stream()
                    .flatMap(menu -> menu.getDaily().stream())
                    .flatMap(daily -> daily.getMeals().stream())
                    .map(meal -> new MealShortInformationDTO(
                            meal.getId(),
                            meal.getName(),
                            meal.getNutritionalInformation().getCalories()
                    ))
                    .toList();
            return new MenuPageDTO(meals);
        }catch (Exception e){
            log.error("Failed to get short meal information ", e);
            throw new MenuException("Failed to get short meal information ", e);
        }
    }
}
