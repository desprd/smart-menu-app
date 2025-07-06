package com.ilyaproject.smart_menu_server.ai;

import com.ilyaproject.smart_menu_server.exception.GenerationException;
import com.ilyaproject.smart_menu_server.model.User;
import com.ilyaproject.smart_menu_server.utils.UserUtils;
import com.ilyaproject.smart_menu_server.dto.RecipesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuGenerator {
    private final OpenAiChatModel model;
    private final PromptGenerator generator;
    private final UserUtils utils;

    public RecipesDTO generate(Authentication authentication) throws Exception{
        try {
            User user = utils.getUserByAuthentication(authentication);
            String json = MenuRequestJsonProvider.json;
            var outputConverter = new BeanOutputConverter<>(RecipesDTO.class);
            Prompt prompt = generator.promptProvider(user, json);
            String response =  model.call(prompt).getResult().getOutput().getText();
            return outputConverter.convert(response);
        }catch (Exception e){
            log.error("Failed to generate menu for user ", e);
            throw new GenerationException("Failed to generate menu for user ", e);
        }
    }

}
