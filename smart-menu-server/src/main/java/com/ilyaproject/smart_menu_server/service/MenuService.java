package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.ai.MenuGenerator;
import com.ilyaproject.smart_menu_server.dto.RecipesDTO;
import com.ilyaproject.smart_menu_server.exception.GenerationException;
import com.ilyaproject.smart_menu_server.model.Recipes;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.DTOToEntity;
import com.ilyaproject.smart_menu_server.utils.EntityToDTO;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new GenerationException("Failed to get menu", e);
        }
    }
}
