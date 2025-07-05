package com.ilyaproject.smart_menu_server.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    @Column(unique = true)
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_information_id", referencedColumnName = "id")
    private ProfileInformation profileInformation;
}
