package com.ilyaproject.smart_menu_server.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public Caffeine caffeineConfig(){
        return Caffeine.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                .expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine){
        CaffeineCacheManager cacheManager = new
                CaffeineCacheManager("meals", "recipes", "profiles");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
    