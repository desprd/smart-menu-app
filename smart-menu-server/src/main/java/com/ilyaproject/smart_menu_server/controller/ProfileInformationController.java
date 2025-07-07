package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.general.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.profile.ProfileInformationRequestDTO;
import com.ilyaproject.smart_menu_server.dto.general.UpdateResponseDTO;
import com.ilyaproject.smart_menu_server.service.ProfileInformationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/information")
@RequiredArgsConstructor
@CrossOrigin
public class ProfileInformationController {
    private final ProfileInformationService service;

    @PutMapping("/update")
    public ResponseEntity<?> updateProfileInformation(@RequestBody @Valid ProfileInformationRequestDTO information,
                                                      Authentication authentication){
        try {
            service.updateProfileInformation(information, authentication);
            UpdateResponseDTO update = new UpdateResponseDTO("Profile Information updated successfully");
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(true, update);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
