package com.ilyaproject.smart_menu_server.ai;

import com.ilyaproject.smart_menu_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptGenerator {
    protected Prompt promptProvider(User user, String json){
        Prompt prompt = new Prompt(getPromptText(user),
                OpenAiChatOptions.builder()
                        .responseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, json))
                        .build());
        return prompt;
    }
    private String getPromptText(User user){
        Double weight = user.getProfileInformation().getWeight();
        Double height = user.getProfileInformation().getHeight();
        Integer age = user.getProfileInformation().getAge();
        String sex = user.getProfileInformation().getSex();
        String activity = user.getProfileInformation().getActivity();
        String goals = user.getProfileInformation().getGoals();
        Boolean isVegetarian = user.getMenuSettings().getIsVegetarian();
        Boolean isGlutenFree = user.getMenuSettings().getIsGlutenFree();
        Boolean isDairyFree = user.getMenuSettings().getIsDairyFree();
        Boolean isNutFree = user.getMenuSettings().getIsNutFree();
        String cuisine = user.getMenuSettings().getCuisine();
        String excluded = user.getMenuSettings().getExcluded();
        String prompt = """
                Create 7 days menu with 3 well balanced meals per day
                (name, recipe, ingredients and nutritional information for every meal)
                according to users personal data: weight %.2f, height %.2f, age %d, sex %s, activity %s, goals %s.
                Users menu preferences: isVegetarian %b, isGlutenFree %b, isDairyFree %b, isNutFree %b, 
                cuisine %s, excluded %s
                """.formatted(weight, height, age, sex, activity,
                goals, isVegetarian, isGlutenFree, isDairyFree,
                isNutFree, cuisine, excluded);
        return prompt;
    }
}
