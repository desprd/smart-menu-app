package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.general.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.profile.MenuSettingsRequestDTO;
import com.ilyaproject.smart_menu_server.dto.general.UpdateResponseDTO;
import com.ilyaproject.smart_menu_server.service.MenuSettingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu/settings")
@RequiredArgsConstructor
@CrossOrigin
public class MenuSettingsController {
    private final MenuSettingsService service;
    @PutMapping("/update")
    public ResponseEntity<?> updateMenuSettings(@RequestBody @Valid MenuSettingsRequestDTO req,
                                                Authentication authentication){
        try {
            service.updateMenuService(req, authentication);
            UpdateResponseDTO update = new UpdateResponseDTO("Menu setting were updated successfully");
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(true, update);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
