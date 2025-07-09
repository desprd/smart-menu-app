import { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import CheckBox from "../components/CheckBox";
import FormSelect from "../components/FormSelect";
import FormTextarea from "../components/FormTextarea";
import SubmitButton from "../components/SubmitButton";
import type { UpdateResponse } from "../types/update";
import axios from "axios";
import type { MenuSettingsResponse } from "../types/settings";
import Loading from "./Loading";

const Recipes: React.FC = () => {
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const API_URL = import.meta.env.VITE_API_URL;
  const token = localStorage.getItem("token");
  const [isVegetarian, setIsVegetarian] = useState(false);
  const [isGlutenFree, setIsGlutenFree] = useState(false);
  const [isDairyFree, setIsDairyFree] = useState(false);
  const [isNutFree, setIsNutFree] = useState(false);
  const [cuisine, setCuisine] = useState("Any");
  const [excluded, setExcluded] = useState("");
  const fetchMenuSettingsData = async () => {
    setIsLoading(true);
    try {
      const response = await axios.get<MenuSettingsResponse>(
        `${API_URL}/menu/settings/get`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.data.success) {
        setIsVegetarian(response.data.content.isVegetarian);
        setIsGlutenFree(response.data.content.isGlutenFree);
        setIsDairyFree(response.data.content.isDairyFree);
        setIsNutFree(response.data.content.isNutFree);
        setCuisine(response.data.content.cuisine);
        setExcluded(response.data.content.excluded);
      } else {
        console.error(response.data.errorMessage);
      }
    } catch (error) {
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };
  const handleSettingsChange = async () => {
    try {
      const response = await axios.put<UpdateResponse>(
        `${API_URL}/menu/settings/update`,
        {
          isVegetarian,
          isGlutenFree,
          isDairyFree,
          isNutFree,
          cuisine,
          excluded,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.data.success) {
        console.log(response.data.content.message);
        setSuccessMessage("Menu settings updated successfully");
      } else {
        console.error(response.data.errorMessage);
        if (response.data.errorMessage != null) {
          console.error(response.data.errorMessage);
        }
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchMenuSettingsData();
  }, []);

  if (isLoading) {
    return (
      <div>
        <Loading loadingtext="Loading your menu settings..." />
      </div>
    );
  }

  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />
          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="flex flex-col gap-1 px-4 py-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Menu Settings
              </h2>
              <p className="text-[#648772] text-sm font-normal leading-normal">
                Customize your weekly menu preferences to meet your dietary
                needs and taste preferences.
              </p>
            </div>

            {errorMessage && (
              <div className="text-red-600 text-sm font-medium px-2 md:px-0 pt-2">
                {errorMessage}
              </div>
            )}
            {successMessage && (
              <div className="text-green-600 text-sm font-medium px-2 md:px-0 pt-2">
                {successMessage}
              </div>
            )}

            {/* Dietary Restrictions */}
            <h3 className="text-[#111714] text-lg font-bold px-4 pb-2 pt-4">
              Dietary Restrictions
            </h3>
            <div className="px-4">
              <CheckBox
                label="Vegetarian"
                checked={isVegetarian}
                setChecked={setIsVegetarian}
              />
              <CheckBox
                label="Gluten-Free"
                checked={isGlutenFree}
                setChecked={setIsGlutenFree}
              />
              <CheckBox
                label="Dairy-Free"
                checked={isDairyFree}
                setChecked={setIsDairyFree}
              />
              <CheckBox
                label="Nut-Free"
                checked={isNutFree}
                setChecked={setIsNutFree}
              />
            </div>

            {/* Cuisine Preferences */}
            <FormSelect
              label="Cuisine Preferences"
              options={["Any", "European", "Asian", "American"]}
              value={cuisine}
              onChange={setCuisine}
            />

            {/* Excluded Ingredients */}
            <FormTextarea
              label="Excluded Ingredients"
              placeholder="List any ingredients you want to avoid in your menu."
              value={excluded}
              onChange={setExcluded}
            />

            {/* Submit */}
            <SubmitButton
              label="Save Settings"
              onClick={handleSettingsChange}
            />
          </main>
        </div>
      </div>
    </div>
  );
};

export default Recipes;
