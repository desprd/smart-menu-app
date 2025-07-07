import React from "react";
import type { Meal } from "../types/meal";

interface MealCardProps {
  meal: Meal;
  key: number;
}

const MealCard: React.FC<MealCardProps> = ({ meal, key }) => {
  return (
    <div className="flex items-center gap-8 bg-white px-4 py-2 rounded-lg">
      <div
        className="bg-center bg-no-repeat bg-cover rounded-lg size-14 flex-shrink-0"
        style={{ backgroundImage: `url(${"/meal.png"})` }}
      ></div>
      <div>
        <p className="text-[#111714] font-medium">{meal.name}</p>
        <p className="text-[#648772] text-sm">{meal.calories}</p>
      </div>
    </div>
  );
};

export default MealCard;
