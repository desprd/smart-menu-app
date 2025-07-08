export type Meal = {
  id: number;
  name: string;
  calories: string;
};

export type DayPlan = {
  day: string;
  meals: Meal[];
};

export type MealServerResponse = {
  success: boolean;
  content: MealInformation;
  errorMessage: string | null;
};

export type MealInformation = {
  mealsShortInformation: Meal[];
};

export type RecipeInformationResponse = {
  success: boolean;
  content: FullRecipeInformation;
  errorMessage: string | null;
};

export type FullRecipeInformation = {
  name: string;
  recipeText: string;
  ingredients: string[];
  calories: string;
  proteins: string;
  fats: string;
  carbohydrates: string;
};
