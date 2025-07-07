import { useState } from "react";
import CheckBox from "../components/CheckBox";
import FormSelect from "../components/FormSelect";
import FormTextarea from "../components/FormTextarea";
import SubmitButton from "../components/SubmitButton";
import type { UpdateResponse } from "../types/update";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { getInitMenu } from "../utils/CreateInitMenu";
import Loading from "./Loading";

const CreateRecipesPage: React.FC = () => {
  const navigate = useNavigate();
  const API_URL = import.meta.env.VITE_API_URL;
  const token = localStorage.getItem("token");
  const [isVegetarian, setIsVegetarian] = useState(false);
  const [isGlutenFree, setIsGlutenFree] = useState(false);
  const [isDairyFree, setIsDairyFree] = useState(false);
  const [isNutFree, setIsNutFree] = useState(false);
  const [cuisine, setCuisine] = useState("Any");
  const [excluded, setExcluded] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const handleSettingsChange = async () => {
    try {
      setIsLoading(true);
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
        if (token != null) {
          await getInitMenu(API_URL, token);
        }
      } else {
        console.error(response.data.errorMessage);
      }
    } catch (error) {
      console.error(error);
    } finally {
      setIsLoading(false);
      navigate("/menu");
    }
  };
  if (isLoading) {
    return (
      <div>
        <Loading loadingtext="Create your personal menu..." />
      </div>
    );
  }
  return (
    <div className="flex min-h-screen items-center justify-center bg-white font-lexend px-4">
      <div className="w-full max-w-xl p-6 rounded-2xl bg-white">
        <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold mb-1">
          Menu Settings
        </h2>
        <p className="text-[#648772] text-sm font-normal mb-4">
          Customize your weekly menu preferences to meet your dietary needs and
          taste preferences.
        </p>

        <h3 className="text-[#111714] text-lg font-bold mb-2">
          Dietary Restrictions
        </h3>
        <div className="mb-4">
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

        <FormSelect
          label="Cuisine Preferences"
          options={["Any", "European", "Asian", "American"]}
          value={cuisine}
          onChange={setCuisine}
        />

        <FormTextarea
          label="Excluded Ingredients"
          placeholder="List any ingredients you want to avoid in your menu."
          value={excluded}
          onChange={setExcluded}
        />

        <div className="pt-4">
          <SubmitButton label="Save Settings" onClick={handleSettingsChange} />
        </div>
      </div>
    </div>
  );
};

export default CreateRecipesPage;
