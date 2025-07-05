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
public class MenuSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Boolean isVegetarian;
    @NotNull
    private Boolean isGlutenFree;
    @NotNull
    private Boolean isDairyFree;
    @NotNull
    private Boolean isNutFree;
    @NotNull
    private String cuisine;
    private String excluded;

}
