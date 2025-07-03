import React from "react";
import Sidebar from "../components/Sidebar";
import Calendar from "../components/Calendar";
import MealSection from "../components/MealSection";

const WeeklyMenu: React.FC = () => {
  const meals = [
    {
      day: "Monday",
      meals: [
        {
          title: "Breakfast: Oatmeal with Berries",
          calories: "350 calories",
        },
        {
          title: "Lunch: Chicken Salad Sandwich",
          calories: "500 calories",
        },
        {
          title: "Dinner: Salmon with Roasted Vegetables",
          calories: "650 calories",
        },
      ],
    },
  ];

  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />

          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="p-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Your weekly menu
              </h2>
            </div>
            <Calendar />

            {meals.map((dayBlock, i) => (
              <MealSection key={i} day={dayBlock.day} meals={dayBlock.meals} />
            ))}
          </main>
        </div>
      </div>
    </div>
  );
};

export default WeeklyMenu;
