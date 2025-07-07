package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.ai.MenuGenerator;
import com.ilyaproject.smart_menu_server.dto.RecipesDTO;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
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
    private final MenuGenerator generator;
    private final UserUtils utils;
    @GetMapping("/init")
    public ResponseEntity<?> initialMenuGeneration(Authentication authentication){
        try {
            User user = utils.getUserByAuthentication(authentication);
            RecipesDTO recipes = generator.generate(user).get();
            log.info("Menu is here");
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
