package com.ilyaproject.smart_menu_server.controller;

import com.ilyaproject.smart_menu_server.dto.GeneralResponse;
import com.ilyaproject.smart_menu_server.dto.LoginRequestDTO;
import com.ilyaproject.smart_menu_server.dto.SignUpRequestDTO;
import com.ilyaproject.smart_menu_server.dto.AuthResponseDTO;
import com.ilyaproject.smart_menu_server.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/signup")
    public ResponseEntity<?>signUp(@RequestBody SignUpRequestDTO req){
        try {
            AuthResponseDTO signUpResponseDTO = service.signUp(req);
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(true, signUpResponseDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            GeneralResponse<AuthResponseDTO> response = new GeneralResponse<>(false, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?>login(@RequestBody LoginRequestDTO req){
        try {
            AuthResponseDTO loginResponseDTO = service.
        }catch (Exception e){

        }
    }
}
