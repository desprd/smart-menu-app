package com.ilyaproject.smart_menu_server.dto.credentials;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequestDTO {
    @NotBlank
    private String token;
    @NotBlank
    @Size(min = 8)
    private String password;
}
