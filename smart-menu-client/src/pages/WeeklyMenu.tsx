import React, { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import Calendar from "../components/Calendar";
import MealSection from "../components/MealSection";
import type { Meal, MealServerResponse } from "../types/meal";
import axios from "axios";
import Loading from "./Loading";

const WeeklyMenu: React.FC = () => {
  const [meals, setMeals] = useState<Meal[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const API_URL = import.meta.env.VITE_API_URL;
  const token = localStorage.getItem("token");
  const fetchMealsData = async () => {
    setIsLoading(true);
    try {
      const response = await axios.get<MealServerResponse>(
        `${API_URL}/menu/generate/get/menupage`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.data.success) {
        setMeals(response.data.content.mealsShortInformation);
      } else {
        console.error(response.data.errorMessage);
      }
    } catch (error) {
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };
  const mealss = [
    {
      day: "Monday",
      meals: [
        {
          id: 12,
          title: "Breakfast: Oatmeal with Berries",
          calories: "350 calories",
        },
        {
          id: 12,
          title: "Lunch: Chicken Salad Sandwich",
          calories: "500 calories",
        },
        {
          id: 12,
          title: "Dinner: Salmon with Roasted Vegetables",
          calories: "650 calories",
        },
      ],
    },
  ];
  useEffect(() => {
    fetchMealsData();
  }, []);

  if (isLoading) {
    return (
      <div>
        <Loading loadingtext="Loading your menu..." />
      </div>
    );
  }

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

            {mealss.map((dayBlock, i) => (
              <MealSection key={i} day={dayBlock.day} meals={dayBlock.meals} />
            ))}
          </main>
        </div>
      </div>
    </div>
  );
};

export default WeeklyMenu;
