package com.ilyaproject.smart_menu_server.service;

public class MenuServerTestUtils {
    public static final String RESPONSE_JSON = """
            {
              "menu": [
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Chicken Teriyaki with Jasmine Rice",
                          "recipeText": "Marinate chicken thighs in teriyaki sauce for 30 minutes. Sauté chicken until cooked. Serve over steamed jasmine rice with steamed broccoli.",
                          "ingredients": [
                            "Chicken thighs",
                            "Teriyaki sauce",
                            "Jasmine rice",
                            "Broccoli",
                            "Soy sauce",
                            "Garlic",
                            "Sesame oil"
                          ],
                          "nutritionalInformation": {
                            "calories": "650 kcal",
                            "proteins": "45 g",
                            "fats": "20 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Beef and Vegetable Stir-Fry",
                          "recipeText": "Slice beef thinly and marinate with soy sauce and ginger. Stir-fry beef in sesame oil, add mixed vegetables and cook until tender. Serve with steamed rice.",
                          "ingredients": [
                            "Beef sirloin",
                            "Soy sauce",
                            "Ginger",
                            "Mixed bell peppers",
                            "Carrots",
                            "Snow peas",
                            "Sesame oil",
                            "Rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "700 kcal",
                            "proteins": "50 g",
                            "fats": "22 g",
                            "carbohydrates": "60 g"
                          }
                        },
                        {
                          "name": "Shrimp Fried Rice",
                          "recipeText": "Cook rice and set aside. Stir-fry shrimp with garlic and vegetables, add cooked rice and soy sauce, then scramble eggs in the mixture and cook until combined.",
                          "ingredients": [
                            "Shrimp",
                            "Cooked rice",
                            "Garlic",
                            "Mixed vegetables (peas, carrots)",
                            "Eggs",
                            "Soy sauce",
                            "Spring onions"
                          ],
                          "nutritionalInformation": {
                            "calories": "680 kcal",
                            "proteins": "42 g",
                            "fats": "18 g",
                            "carbohydrates": "75 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Grilled Salmon with Ginger Soy Glaze",
                          "recipeText": "Prepare a glaze with soy sauce, ginger, garlic, and honey. Grill salmon and brush with glaze. Serve with steamed bok choy and brown rice.",
                          "ingredients": [
                            "Salmon fillet",
                            "Soy sauce",
                            "Ginger",
                            "Garlic",
                            "Honey",
                            "Bok choy",
                            "Brown rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "720 kcal",
                            "proteins": "48 g",
                            "fats": "25 g",
                            "carbohydrates": "65 g"
                          }
                        },
                        {
                          "name": "Chicken Pho Bowl",
                          "recipeText": "Prepare pho broth with spices and simmer chicken. Cook rice noodles. Assemble bowl with shredded chicken, noodles, broth, and fresh herbs.",
                          "ingredients": [
                            "Chicken breast",
                            "Pho spices (star anise, cinnamon)",
                            "Rice noodles",
                            "Bean sprouts",
                            "Cilantro",
                            "Lime",
                            "Chili"
                          ],
                          "nutritionalInformation": {
                            "calories": "680 kcal",
                            "proteins": "50 g",
                            "fats": "15 g",
                            "carbohydrates": "80 g"
                          }
                        },
                        {
                          "name": "Pork and Vegetable Spring Rolls",
                          "recipeText": "Cook ground pork with spices. Soften rice paper sheets, fill with pork, lettuce, carrots, and herbs. Serve with peanut dipping sauce.",
                          "ingredients": [
                            "Ground pork",
                            "Rice paper",
                            "Lettuce",
                            "Carrots",
                            "Mint",
                            "Peanut sauce"
                          ],
                          "nutritionalInformation": {
                            "calories": "650 kcal",
                            "proteins": "40 g",
                            "fats": "20 g",
                            "carbohydrates": "60 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Spicy Tofu Stir Fry with Rice",
                          "recipeText": "Sauté tofu cubes with chili garlic sauce and mixed vegetables. Serve over steamed jasmine rice.",
                          "ingredients": [
                            "Tofu",
                            "Chili garlic sauce",
                            "Mixed vegetables",
                            "Jasmine rice",
                            "Soy sauce",
                            "Garlic",
                            "Sesame oil"
                          ],
                          "nutritionalInformation": {
                            "calories": "600 kcal",
                            "proteins": "38 g",
                            "fats": "18 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Beef Bulgogi with Kimchi",
                          "recipeText": "Marinate beef slices in soy sauce, sugar, garlic and sesame oil. Grill and serve with kimchi and steamed rice.",
                          "ingredients": [
                            "Beef slices",
                            "Soy sauce",
                            "Sugar",
                            "Garlic",
                            "Sesame oil",
                            "Kimchi",
                            "Rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "710 kcal",
                            "proteins": "52 g",
                            "fats": "23 g",
                            "carbohydrates": "65 g"
                          }
                        },
                        {
                          "name": "Egg Fried Rice with Vegetables",
                          "recipeText": "Stir-fry cooked rice with eggs, mixed vegetables and soy sauce until combined and heated through.",
                          "ingredients": [
                            "Cooked rice",
                            "Eggs",
                            "Mixed vegetables",
                            "Soy sauce",
                            "Spring onions",
                            "Garlic"
                          ],
                          "nutritionalInformation": {
                            "calories": "650 kcal",
                            "proteins": "35 g",
                            "fats": "20 g",
                            "carbohydrates": "75 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Grilled Chicken Satay with Peanut Sauce",
                          "recipeText": "Marinate chicken chunks with turmeric and spices, grill on skewers. Serve with a side of peanut sauce and cucumber salad.",
                          "ingredients": [
                            "Chicken breast",
                            "Turmeric",
                            "Cumin",
                            "Peanut sauce",
                            "Cucumber",
                            "Rice vinegar",
                            "Sugar"
                          ],
                          "nutritionalInformation": {
                            "calories": "690 kcal",
                            "proteins": "50 g",
                            "fats": "22 g",
                            "carbohydrates": "60 g"
                          }
                        },
                        {
                          "name": "Shrimp Pad Thai",
                          "recipeText": "Cook rice noodles. Stir-fry shrimp with garlic, eggs, tamarind paste, fish sauce, sugar, and peanuts. Toss noodles and garnish with lime and cilantro.",
                          "ingredients": [
                            "Shrimp",
                            "Rice noodles",
                            "Garlic",
                            "Eggs",
                            "Tamarind paste",
                            "Fish sauce",
                            "Sugar",
                            "Crushed peanuts",
                            "Lime",
                            "Cilantro"
                          ],
                          "nutritionalInformation": {
                            "calories": "720 kcal",
                            "proteins": "45 g",
                            "fats": "25 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Beef and Broccoli Stir Fry",
                          "recipeText": "Stir-fry thinly sliced beef and broccoli florets with garlic, ginger, soy sauce, and oyster sauce. Serve with steamed rice.",
                          "ingredients": [
                            "Beef strips",
                            "Broccoli",
                            "Garlic",
                            "Ginger",
                            "Soy sauce",
                            "Oyster sauce",
                            "Rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "680 kcal",
                            "proteins": "48 g",
                            "fats": "20 g",
                            "carbohydrates": "65 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Chicken Curry with Jasmine Rice",
                          "recipeText": "Simmer chicken pieces in coconut milk with curry paste and vegetables. Serve over jasmine rice.",
                          "ingredients": [
                            "Chicken thighs",
                            "Coconut milk",
                            "Red curry paste",
                            "Bell peppers",
                            "Bamboo shoots",
                            "Jasmine rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "720 kcal",
                            "proteins": "47 g",
                            "fats": "25 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Beef Pho with Fresh Herbs",
                          "recipeText": "Prepare beef broth over several hours with bones and spices. Serve with rice noodles, sliced beef, bean sprouts, herbs, and lime.",
                          "ingredients": [
                            "Beef bones",
                            "Beef slices",
                            "Rice noodles",
                            "Star anise",
                            "Cinnamon",
                            "Bean sprouts",
                            "Cilantro",
                            "Lime"
                          ],
                          "nutritionalInformation": {
                            "calories": "700 kcal",
                            "proteins": "50 g",
                            "fats": "18 g",
                            "carbohydrates": "75 g"
                          }
                        },
                        {
                          "name": "Grilled Pork Banh Mi Sandwich",
                          "recipeText": "Grill marinated pork slices. Assemble sandwich with baguette, pickled vegetables, cilantro, and mayonnaise.",
                          "ingredients": [
                            "Pork slices",
                            "Baguette",
                            "Pickled carrots and daikon",
                            "Cilantro",
                            "Mayonnaise",
                            "Soy sauce"
                          ],
                          "nutritionalInformation": {
                            "calories": "720 kcal",
                            "proteins": "45 g",
                            "fats": "22 g",
                            "carbohydrates": "80 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Teriyaki Beef Bowl",
                          "recipeText": "Cook beef strips with teriyaki sauce. Serve over steamed rice with steamed vegetables and garnish with sesame seeds.",
                          "ingredients": [
                            "Beef strips",
                            "Teriyaki sauce",
                            "Rice",
                            "Broccoli",
                            "Carrots",
                            "Sesame seeds"
                          ],
                          "nutritionalInformation": {
                            "calories": "700 kcal",
                            "proteins": "50 g",
                            "fats": "23 g",
                            "carbohydrates": "65 g"
                          }
                        },
                        {
                          "name": "Thai Green Curry with Chicken",
                          "recipeText": "Simmer chicken pieces in green curry paste with coconut milk and vegetables. Serve with jasmine rice.",
                          "ingredients": [
                            "Chicken breast",
                            "Green curry paste",
                            "Coconut milk",
                            "Eggplant",
                            "Basil",
                            "Jasmine rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "730 kcal",
                            "proteins": "48 g",
                            "fats": "26 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Shrimp and Vegetable Stir Fry",
                          "recipeText": "Stir-fry shrimp with mixed vegetables, garlic, and soy sauce. Serve with steamed brown rice.",
                          "ingredients": [
                            "Shrimp",
                            "Mixed vegetables",
                            "Garlic",
                            "Soy sauce",
                            "Brown rice"
                          ],
                          "nutritionalInformation": {
                            "calories": "650 kcal",
                            "proteins": "43 g",
                            "fats": "17 g",
                            "carbohydrates": "65 g"
                          }
                        }
                      ]
                    }
                  ]
                },
                {
                  "daily": [
                    {
                      "meals": [
                        {
                          "name": "Miso Glazed Salmon with Rice",
                          "recipeText": "Glaze salmon fillet with miso paste and broil until cooked. Serve with steamed rice and sautéed spinach.",
                          "ingredients": [
                            "Salmon fillet",
                            "Miso paste",
                            "Rice",
                            "Spinach",
                            "Soy sauce",
                            "Sesame oil"
                          ],
                          "nutritionalInformation": {
                            "calories": "710 kcal",
                            "proteins": "49 g",
                            "fats": "24 g",
                            "carbohydrates": "65 g"
                          }
                        },
                        {
                          "name": "Chicken Fried Rice",
                          "recipeText": "Stir-fry cooked rice with diced chicken, eggs, peas, carrots, and soy sauce until well combined and heated.",
                          "ingredients": [
                            "Cooked rice",
                            "Chicken breast",
                            "Eggs",
                            "Peas",
                            "Carrots",
                            "Soy sauce",
                            "Garlic"
                          ],
                          "nutritionalInformation": {
                            "calories": "670 kcal",
                            "proteins": "46 g",
                            "fats": "20 g",
                            "carbohydrates": "70 g"
                          }
                        },
                        {
                          "name": "Beef and Noodle Soup",
                          "recipeText": "Prepare beef broth and cook rice noodles. Add beef slices, bok choy, and herbs. Serve hot.",
                          "ingredients": [
                            "Beef broth",
                            "Beef slices",
                            "Rice noodles",
                            "Bok choy",
                            "Cilantro",
                            "Green onions"
                          ],
                          "nutritionalInformation": {
                            "calories": "680 kcal",
                            "proteins": "50 g",
                            "fats": "18 g",
                            "carbohydrates": "70 g"
                          }
                        }
                      ]
                    }
                  ]
                }
              ]
            }
            """;
}
