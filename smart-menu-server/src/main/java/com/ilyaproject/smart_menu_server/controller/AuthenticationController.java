package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.auth.AuthResponseDTO;
import com.ilyaproject.smart_menu_server.dto.auth.LoginRequestDTO;
import com.ilyaproject.smart_menu_server.dto.auth.SignUpRequestDTO;
import com.ilyaproject.smart_menu_server.dto.general.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.general.VerifyResponseDTO;
import com.ilyaproject.smart_menu_server.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/signup")
    public ResponseEntity<?>signUp(@RequestBody @Valid SignUpRequestDTO req){
        try {
            AuthResponseDTO signUpResponseDTO = service.signUp(req);
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(true, signUpResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody @Valid LoginRequestDTO req){
        try {
            AuthResponseDTO loginResponseDTO = service.login(req);
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(true, loginResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/verify")
    public ResponseEntity<?>verify(Authentication authentication){
        try {
            VerifyResponseDTO verifyResponseDTO = service.verify(authentication);
            GeneralResponse<VerifyResponseDTO> response = new GeneralResponse<>(true, verifyResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            GeneralResponse<VerifyResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
