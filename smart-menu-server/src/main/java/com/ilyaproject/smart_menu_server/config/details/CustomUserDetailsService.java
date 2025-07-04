package com.ilyaproject.smart_menu_server.config.details;

import com.ilyaproject.smart_menu_server.exception.IDNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserDetails loadUserById(Integer id) throws IDNotFoundException;
}
