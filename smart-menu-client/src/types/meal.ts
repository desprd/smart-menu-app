export type Meal = {
  title: string;
  calories: string;
};

export type DayPlan = {
  day: string;
  meals: Meal[];
};
