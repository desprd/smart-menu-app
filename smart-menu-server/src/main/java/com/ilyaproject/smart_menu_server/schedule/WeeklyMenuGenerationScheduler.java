package com.ilyaproject.smart_menu_server.schedule;

import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.repository.UserRepository;
import com.ilyaproject.smart_menu_server.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeeklyMenuGenerationScheduler {
    private final UserRepository userRepository;
    private final MenuService service;
    @Scheduled(cron = "0 0 5 * * MON", zone = "Europe/Warsaw")
    public void sendWeeklyMenuToAllUsers(){
        List<User> users = userRepository.findAllWithAllRelations();
        for (User user: users){
            try {
                service.weeklyMenuGeneration(user);
            }catch (Exception e){
                log.error("Failed to generate menu in scheduling ", e);
            }finally {
                log.info("Scheduler is working");
            }
        }
    }

}
