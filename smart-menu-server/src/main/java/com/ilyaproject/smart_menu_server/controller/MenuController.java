package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.general.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.menu.json.RecipesDTO;
import com.ilyaproject.smart_menu_server.dto.general.UpdateResponseDTO;
import com.ilyaproject.smart_menu_server.dto.menu.request.MenuPageDTO;
import com.ilyaproject.smart_menu_server.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/menu/generate")
@RequiredArgsConstructor
@CrossOrigin
public class MenuController {
    private final MenuService service;
    @GetMapping("/init")
    public ResponseEntity<?> initialMenuGeneration(Authentication authentication){
        try {
            service.initialMenuGeneration(authentication);
            UpdateResponseDTO update = new UpdateResponseDTO("Menu instance was created successfully");
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(true, update);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUserMenu(Authentication authentication){
        try {
            RecipesDTO recipes = service.getUserRecipes(authentication);
            GeneralResponse<RecipesDTO> response = new GeneralResponse<>(true, recipes);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/menupage")
    public ResponseEntity<?> getInformationForMenuPage(Authentication authentication){
        try {
            MenuPageDTO menuPage = service.getInformationForMenuPage(authentication);
            GeneralResponse<MenuPageDTO> response = new GeneralResponse<>(true, menuPage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
