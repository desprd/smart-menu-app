package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.profile.ProfileInformationRequestDTO;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileInformationService  {
    private final UserRepository repository;
    @Transactional
    public void updateProfileInformation(@RequestBody @Valid ProfileInformationRequestDTO information,
                                            Authentication authentication) throws Exception{
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        var userId = userDetails.getUser().getId();
        var user = repository.findById(userId)
                .orElseThrow(() -> new ProfileException("User not found"));
        try {
            var profileInfo = user.getProfileInformation();
            profileInfo.setActivity(information.getActivity());
            profileInfo.setAge(information.getAge());
            profileInfo.setSex(information.getSex());
            profileInfo.setWeight(information.getWeight());
            profileInfo.setHeight(information.getHeight());
            profileInfo.setGoals(information.getGoals());
            user.setProfileInformation(profileInfo);
            repository.save(user);
        }catch (Exception e){
            log.error(e.toString());
            throw new ProfileException("Failed to update profile");
        }
    }
}
