import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import type {
  FullRecipeInformation,
  RecipeInformationResponse,
} from "../types/meal";
import Loading from "./Loading";

const Recipe: React.FC = () => {
  const [isLoading, setIsLoading] = useState(false);
  const { id } = useParams();
  const API_URL = import.meta.env.VITE_API_URL;
  const token = localStorage.getItem("token");
  const [recipeInfo, setRecipeInfo] = useState<FullRecipeInformation>();
  const fetchRecipeData = async () => {
    setIsLoading(true);
    try {
      const response = await axios.get<RecipeInformationResponse>(
        `${API_URL}/menu/generate/get/recipe/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.data.success) {
        setRecipeInfo(response.data.content);
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
    fetchRecipeData();
  }, []);

  if (isLoading) {
    return (
      <div>
        <Loading loadingtext="Loading your menu..." />
      </div>
    );
  }

  return (
    <div className="relative flex min-h-screen flex-col bg-white overflow-x-hidden font-lexend">
      <main className="px-10 py-5 flex justify-center">
        <div className="max-w-[960px] w-full">
          <div className="flex gap-2 p-4 text-[#648772] text-base font-medium">
            <Link to={"/menu"}>Menu</Link>
            <span>/</span>
            <span className="text-[#111714]">{recipeInfo?.name}</span>
          </div>

          <div className="p-4">
            <h1 className="text-[32px] font-bold text-[#111714] leading-tight mb-2">
              {recipeInfo?.name}
            </h1>
          </div>

          <div className="p-4 flex flex-col xl:flex-row gap-4 min-h-[300px]">
            {/* Image block */}
            <div
              className="w-full xl:w-1/2 min-h-[300px] bg-cover bg-center rounded-xl"
              style={{
                backgroundImage: `url(${"/food.png"})`,
              }}
            ></div>
          </div>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Ingredients
            </h2>
            <ul className="flex flex-col gap-x-6">
              {recipeInfo?.ingredients.map((ingredient) => (
                <React.Fragment>
                  <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                    {ingredient}
                  </li>
                </React.Fragment>
              ))}
            </ul>
          </section>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Instructions
            </h2>
            <p className="text-base text-[#111714]">{recipeInfo?.recipeText}</p>
          </section>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Nutritional Information
            </h2>
            <ul className="grid grid-cols-[20%_1fr] gap-x-6">
              <React.Fragment>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                  Calories
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                  {recipeInfo?.calories}
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                  Protein
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                  {recipeInfo?.proteins}
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                  Fat
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                  {recipeInfo?.fats}
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                  Carbohydrates
                </li>
                <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                  {recipeInfo?.carbohydrates}
                </li>
              </React.Fragment>
            </ul>
          </section>
        </div>
      </main>
    </div>
  );
};

export default Recipe;
