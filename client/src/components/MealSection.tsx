import React from "react";
import type { DayPlan } from "../types/meal";

const MealSection: React.FC<DayPlan> = ({ day, meals }) => {
  return (
    <section className="px-4 py-5">
      <h3 className="text-[#111714] text-xl font-bold mb-3">{day}</h3>
      <div className="space-y-3">
        {meals.map((meal, index) => (
          <div
            key={index}
            className="flex items-center gap-14 bg-white px-4 py-2 rounded-lg"
          >
            <div
              className="bg-center bg-no-repeat bg-cover rounded-lg size-14 flex-shrink-0"
              style={{ backgroundImage: `url(${"/meal.png"})` }}
            ></div>
            <div>
              <p className="text-[#111714] font-medium">{meal.title}</p>
              <p className="text-[#648772] text-sm">{meal.calories}</p>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
};

export default MealSection;
