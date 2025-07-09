package com.ilyaproject.smart_menu_server.service;

import com.ilyaproject.smart_menu_server.config.details.CustomUserDetails;
import com.ilyaproject.smart_menu_server.dto.profile.ProfileInformationRequestDTO;
import com.ilyaproject.smart_menu_server.exception.ProfileException;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileInformationService  {
    private final UserRepository repository;
    private final UserUtils utils;
    @Transactional
    @CacheEvict(cacheNames = "profiles", key = "#authentication.name")
    public void updateProfileInformation(@RequestBody @Valid ProfileInformationRequestDTO information,
                                            Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
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

    @Cacheable(value = "profiles", key = "#authentication.name")
    public ProfileInformationRequestDTO getProfileInformation(Authentication authentication) throws Exception{
        User user = utils.getUserByAuthentication(authentication);
        try {
            var profileInfo = user.getProfileInformation();
            return ProfileInformationRequestDTO
                    .builder()
                    .sex(profileInfo.getSex())
                    .age(profileInfo.getAge())
                    .activity(profileInfo.getActivity())
                    .goals(profileInfo.getGoals())
                    .height(profileInfo.getHeight())
                    .weight(profileInfo.getWeight())
                    .build();
        }catch (Exception e){
            log.error("Failed to get profile information ", e);
            throw new ProfileException("Failed to get profile information ", e);
        }
    }
}
