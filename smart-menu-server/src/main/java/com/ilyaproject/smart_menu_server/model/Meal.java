package com.ilyaproject.smart_menu_server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String recipeText;
    @ElementCollection
    private List<String> ingredients;
    @Embedded
    private NutritionalInformation nutritionalInformation;
}
