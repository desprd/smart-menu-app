package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.ai.MenuGenerator;
import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MealFullInformationDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MealShortInformationDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MenuPageDTO;
import com.ilyaproject.smart_menu_server.email.sender.RecipesInPdfEmailSender;
import com.ilyaproject.smart_menu_server.model.*;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.Cleaner;
import com.ilyaproject.smart_menu_server.utils.DTOToEntity;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @MockitoBean
    private MenuGenerator menuGenerator;

    @MockitoBean
    private RecipesInPdfEmailSender sender;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOToEntity dte;

    private User user;

    private Authentication auth;

    private RecipesDTO recipesDTO;

    @Autowired
    private Cleaner cleaner;

    private static Meal getFirstMeal(User u){
        return u.getRecipes().getMenu().getFirst().getDaily().getFirst().getMeals().getFirst();
    }

    @BeforeEach
    void setUp(){
        var profileInfo = ProfileInformation
                .builder()
                .weight(60.0)
                .height(180.0)
                .age(21)
                .sex("Male")
                .activity("Moderate")
                .goals("Maintenance")
                .build();
        var menuSettings = MenuSettings
                .builder()
                .isDairyFree(false)
                .isGlutenFree(false)
                .isNutFree(false)
                .isVegetarian(false)
                .cuisine("Any")
                .excluded("")
                .build();
        user = User
                .builder()
                .username("User")
                .email(cleaner.cleanEmail("email@email.com"))
                .role(Role.USER)
                .password(encoder.encode("password"))
                .profileInformation(profileInfo)
                .menuSettings(menuSettings)
                .build();
        userRepository.save(user);
        CustomUserDetails details = new CustomUserDetails(user);
        auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
        var outputConverter = new BeanOutputConverter<>(RecipesDTO.class);
        recipesDTO = outputConverter.convert(MenuServerTestUtils.RESPONSE_JSON);
    }

    @Test
    void menuGenerationTest_void() throws Exception{
        assertNotNull(recipesDTO);
        when(menuGenerator.generate(any(User.class))).thenReturn(CompletableFuture.completedFuture(recipesDTO));
        doNothing().when(sender).sendRecipesInPdfEmail(anyString(), any(RecipesDTO.class));
        menuService.menuGeneration(auth);
        User saved = userRepository.findById(user.getId()).orElseThrow();
        assertNotNull(saved.getRecipes());
        assertEquals("Chicken Teriyaki with Jasmine Rice", getFirstMeal(saved).getName());
        verify(menuGenerator).generate(any(User.class));
        verify(sender).sendRecipesInPdfEmail(anyString(), eq(recipesDTO));
        verifyNoMoreInteractions(menuGenerator, sender);
    }

    @Test
    void getUserRecipesTest_returnUserRecipesDTO() throws Exception{
        assertNotNull(recipesDTO);
        Recipes recipes = dte.mapRecipesDTOToEntity(recipesDTO);
        user.setRecipes(recipes);
        userRepository.save(user);
        CustomUserDetails details = new CustomUserDetails(user);
        auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
        RecipesDTO responseRecipes = menuService.getUserRecipes(auth);
        assertNotNull(responseRecipes);
        assertEquals(recipesDTO, responseRecipes);
    }

    @Test
    void getInformationForMenuPageTest_returnMenuPageDTO() throws Exception{
        assertNotNull(recipesDTO);
        Recipes recipes = dte.mapRecipesDTOToEntity(recipesDTO);
        user.setRecipes(recipes);
        userRepository.save(user);
        CustomUserDetails details = new CustomUserDetails(user);
        auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
        MenuPageDTO menuPage = menuService.getInformationForMenuPage(auth);
        assertEquals("Chicken Teriyaki with Jasmine Rice" , menuPage.getMealsShortInformation().getFirst().getName());
    }

    @Test
    void getFullRecipeByIdTest_returnMealFullInformationDTO() throws Exception{
        assertNotNull(recipesDTO);
        Recipes recipes = dte.mapRecipesDTOToEntity(recipesDTO);
        user.setRecipes(recipes);
        userRepository.save(user);
        CustomUserDetails details = new CustomUserDetails(user);
        auth = new UsernamePasswordAuthenticationToken(
                details, null, details.getAuthorities()
        );
        MealFullInformationDTO fullInfo = menuService.getFullRecipeById(getFirstMeal(user).getId());
        assertNotNull(fullInfo);
        assertEquals("Chicken Teriyaki with Jasmine Rice", fullInfo.getName());

    }
}
