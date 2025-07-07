package com.ilyaproject.smart_menu_server.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthResponseDTO {
    private String username;
    private String email;
    private String token;
}
