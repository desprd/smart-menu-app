package com.ilyaproject.smart_menu_server.utils;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository repository;
    public User getUserByAuthentication(Authentication authentication) throws Exception{
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var userId = userDetails.getUser().getId();
        var user = repository.findById(userId)
                .orElseThrow(() -> new ProfileException("User not found"));
        return user;
    }
}
