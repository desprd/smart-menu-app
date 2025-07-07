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

  useEffect(() => {
    fetchMealsData();
  }, []);

  useEffect(() => {
    console.log(meals);
  }, [meals]);

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
            <MealSection day={"Monday"} meals={meals.slice(0, 3)} />
            <MealSection day={"Tuesday"} meals={meals.slice(3, 6)} />
            <MealSection day={"Wednesday"} meals={meals.slice(6, 9)} />
            <MealSection day={"Thursday"} meals={meals.slice(9, 12)} />
            <MealSection day={"Friday"} meals={meals.slice(12, 15)} />
            <MealSection day={"Saturday"} meals={meals.slice(15, 18)} />
            <MealSection day={"Sunday"} meals={meals.slice(18, 21)} />
          </main>
        </div>
      </div>
    </div>
  );
};

export default WeeklyMenu;
