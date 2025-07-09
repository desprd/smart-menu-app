package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.credentials.ChangeCredentialsDTO;
import com.ilyaproject.smart_menu_server.dto.general.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.general.UpdateResponseDTO;
import com.ilyaproject.smart_menu_server.service.CredentialsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
@CrossOrigin
public class CredentialsController {
    private final CredentialsService service;
    @PutMapping("/change")
    public ResponseEntity<?> changeCredentials(@RequestBody @Valid ChangeCredentialsDTO req,
                                               Authentication authentication) throws Exception{
        try {
            service.changeCredentials(req, authentication);
            UpdateResponseDTO update = new UpdateResponseDTO("Users credentials were changed successfully");
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse(true, update);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
