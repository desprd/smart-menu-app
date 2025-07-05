package com.ilyaproject.smart_menu_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @NotNull
    private Integer age;
    @NotNull
    private String sex;
    @NotNull
    private String activity;
    @NotNull
    private String goals;
}
