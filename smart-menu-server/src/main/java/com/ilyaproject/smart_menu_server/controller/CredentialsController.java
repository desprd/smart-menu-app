package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.auth.TokenDTO;
import com.ilyaproject.smart_menu_server.dto.credentials.ChangeCredentialsDTO;
import com.ilyaproject.smart_menu_server.dto.credentials.ResetPasswordRequestDTO;
import com.ilyaproject.smart_menu_server.dto.email.EmailDTO;
import com.ilyaproject.smart_menu_server.dto.general.*;
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
                                               Authentication authentication){
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

    @PostMapping("/sendlink")
    public ResponseEntity<?> sendLinkToChangePassword(@RequestBody EmailDTO req){
        try {
            service.sendLinkToChangePassword(req.getEmail());
            SendLinkResponseDTO send = new SendLinkResponseDTO("Link to change password was sent ");
            GeneralResponse<SendLinkResponseDTO> response = new GeneralResponse<>(true, send);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<SendLinkResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validatetoken")
    public ResponseEntity<?> validateToken(@RequestBody TokenDTO req){
        try {
            service.validateToken(req.getToken());
            ValidationResponseDTO validation = new ValidationResponseDTO("Token is valid");
            GeneralResponse<ValidationResponseDTO> response = new GeneralResponse<>(true, validation);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<ValidationResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequestDTO req){
        try {
            service.resetPassword(req.getToken(), req.getPassword());
            UpdateResponseDTO update = new UpdateResponseDTO("Users password updated successfully");
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(true, update);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<UpdateResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
