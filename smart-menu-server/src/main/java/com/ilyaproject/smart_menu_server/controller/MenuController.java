package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.ai.MenuGenerator;
import com.ilyaproject.smart_menu_server.dto.RecipesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu/generate")
@RequiredArgsConstructor
@CrossOrigin
public class MenuController {
    private final MenuGenerator generator;
    @GetMapping("/init")
    public ResponseEntity<?> initialMenuGeneration(Authentication authentication){
        try {
            RecipesDTO recipes = generator.generate(authentication);
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
