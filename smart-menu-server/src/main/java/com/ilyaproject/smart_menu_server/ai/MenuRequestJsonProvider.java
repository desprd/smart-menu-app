package com.ilyaproject.smart_menu_server.ai;

public class MenuRequestJsonProvider {
    public static final String json = """
            {
               "$schema": "http://json-schema.org/draft-07/schema#",
               "title": "Recipes",
               "type": "object",
               "properties": {
                 "menu": {
                   "type": "array",
                   "description": "List of daily menus",
                   "items": {
                     "type": "object",
                     "properties": {
                       "daily": {
                         "type": "array",
                         "description": "List of days",
                         "items": {
                           "type": "object",
                           "properties": {
                             "meals": {
                               "type": "array",
                               "description": "Meals for the day",
                               "items": {
                                 "type": "object",
                                 "properties": {
                                   "name": {
                                     "type": "string",
                                     "description": "Name of the recipe"
                                   },
                                   "recipeText": {
                                     "type": "string",
                                     "description": "Recipe for this dish"
                                   },
                                   "ingredients": {
                                     "type": "array",
                                     "description": "List of ingredients",
                                     "items": {
                                       "type": "string"
                                     }
                                   },
                                   "nutritionalInformation": {
                                     "type": "object",
                                     "description": "Nutritional details for the recipe",
                                     "properties": {
                                       "calories": {
                                         "type": "string",
                                         "description": "Calories in this recipe"
                                       },
                                       "proteins": {
                                         "type": "string",
                                         "description": "Protein in this recipe"
                                       },
                                       "fats": {
                                         "type": "string",
                                         "description": "Fats in this recipe"
                                       },
                                       "carbohydrates": {
                                         "type": "string",
                                         "description": "Carbohydrates in this recipe"
                                       }
                                     },
                                     "required": ["calories", "proteins", "fats", "carbohydrates"],
                                     "additionalProperties": false
                                   }
                                 },
                                 "required": ["name", "recipeText", "ingredients", "nutritionalInformation"],
                                 "additionalProperties": false
                               }
                             }
                           },
                           "required": ["meals"],
                           "additionalProperties": false
                         }
                       }
                     },
                     "required": ["daily"],
                     "additionalProperties": false
                   }
                 }
               },
               "required": ["menu"],
               "additionalProperties": false
             }
             
                        
            """;
}
