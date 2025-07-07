import React from "react";
import type { DayPlan } from "../types/meal";
import MealCard from "./MealCard";

const MealSection: React.FC<DayPlan> = ({ day, meals }) => {
  return (
    <section className="px-4 py-5">
      <h3 className="text-[#111714] text-xl font-bold mb-3">{day}</h3>
      <div className="space-y-3">
        {meals.map((meal) => (
          <MealCard meal={meal} key={meal.id} />
        ))}
      </div>
    </section>
  );
};

export default MealSection;
