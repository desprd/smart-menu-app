package com.ilyaproject.smart_menu_server.email.generator.html;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HtmlProvider {
    public String getHtml(String name, List<String> ingredients, String recipeText,
                          String calories, String proteins, String fats, String carbs){
        String ingredientsSection = getIngredientsHtmlFromObject(ingredients);
        return String.format("""
    <html>
      <head>
        <title>Smart Menu - Quinoa Salad</title>
        <style>
          body {
            font-family: sans-serif;
            margin: 40px;
            font-size: 15px;
            line-height: 1.4;
            color: #111;
          }
          h1, h2 {
            margin-bottom: 5px;
          }
          img {
            max-width: 100%%;
            height: auto;
            border-radius: 8px;
          }
          ul, ol {
            padding-left: 20px;
          }
          .section {
            margin-bottom: 20px;
          }
        </style>
      </head>
      <body>
        <h1>%s</h1>
        <div class="section">
          <h2>Ingredients</h2>
          <ul>
            %s
          </ul>
        </div>
    
        <div class="section">
          <h2>Instructions</h2>
          <ol>
            <p>%s</p>
          </ol>
        </div>
    
        <div class="section">
          <h2>Nutritional Info (per serving)</h2>
          <ul>
            <li>Calories: %s</li>
            <li>Protein: %s</li>
            <li>Fat: %s</li>
            <li>Carbs: %s</li>
          </ul>
        </div>
      </body>
    </html>
    """, name, ingredientsSection, recipeText, calories, proteins, fats, carbs);
    }

    private String getIngredientsHtmlFromObject(List<String> ingredients){
        StringBuilder sb = new StringBuilder();
        for (String ingredient: ingredients){
            sb.append("<li>").append(ingredient).append("</li>\n");
        }
        return sb.toString();
    }
}
