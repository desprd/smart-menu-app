import { useState } from "react";
import Sidebar from "../components/Sidebar";
import CheckBox from "../components/CheckBox";
import FormSelect from "../components/FormSelect";
import FormTextarea from "../components/FormTextarea";

const Recipes: React.FC = () => {
  const [isVegeterian, setIsVegeterian] = useState(false);
  const [isGlutenFree, setIsGlutenFree] = useState(false);
  const [isDairyFree, setIsDairyFree] = useState(false);
  const [isNutFree, setIsNutFree] = useState(false);
  const [cuisine, setCuisine] = useState("Any");
  const [excluded, setExcluded] = useState("");
  const handleSettingsChange = async () => {
    alert(
      `Logging in with: ${isVegeterian}, ${isGlutenFree}, ${isDairyFree}, ${isNutFree}, ${cuisine}, ${excluded}`
    );
  };
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

            {/* Dietary Restrictions */}
            <h3 className="text-[#111714] text-lg font-bold px-4 pb-2 pt-4">
              Dietary Restrictions
            </h3>
            <div className="px-4">
              <CheckBox
                label="Vegetarian"
                checked={isVegeterian}
                setChecked={setIsVegeterian}
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
            <div className="flex px-4 py-3 justify-end">
              <button
                onClick={handleSettingsChange}
                className="cursor-pointer rounded-xl bg-[#38e07b] text-[#111714] h-10 px-4 text-sm font-bold tracking-[0.015em]"
              >
                Save Settings
              </button>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
};

export default Recipes;
