package com.ilyaproject.smart_menu_server.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfileInformationRequestDTO {
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @NotNull
    private Integer age;
    @NotBlank
    private String sex;
    @NotBlank
    private String activity;
    @NotBlank
    private String goals;
}

