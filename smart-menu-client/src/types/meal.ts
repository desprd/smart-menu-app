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
