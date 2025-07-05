import { useState } from "react";
import CheckBox from "../components/CheckBox";
import FormSelect from "../components/FormSelect";
import FormTextarea from "../components/FormTextarea";
import SubmitButton from "../components/SubmitButton";

const CreateRecipesPage: React.FC = () => {
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
