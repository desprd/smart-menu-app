import React, { useState } from "react";
import FormInput from "../components/FormInput";
import FormSelect from "../components/FormSelect";
import { isBlank } from "../utils/dataCheck";
import axios from "axios";
import type { UpdateResponse } from "../types/update";
import { useNavigate } from "react-router-dom";
const CreateProfilePage: React.FC = () => {
  const API_URL = import.meta.env.VITE_API_URL;
  const token = localStorage.getItem("token");
  const [weight, setWeight] = useState("");
  const [height, setHeight] = useState("");
  const [age, setAge] = useState("");
  const [sex, setSex] = useState("");
  const [activity, setActivity] = useState("");
  const [goals, setGoals] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  const handleLogin = async () => {
    if (
      isBlank(weight) ||
      isBlank(height) ||
      isBlank(age) ||
      isBlank(sex) ||
      isBlank(activity) ||
      isBlank(goals)
    ) {
      setErrorMessage("Fields are supposed to be not blank");
      return;
    }
    if (Number(weight) < 30 || Number(weight) > 300) {
      setErrorMessage("Please enter valid weight");
      return;
    }
    if (Number(height) < 140 || Number(height) > 220) {
      setErrorMessage("Please enter valid height");
      return;
    }
    if (Number(age) < 12 || Number(age) > 150) {
      setErrorMessage("Please enter valid age");
      return;
    }
    try {
      const response = await axios.put<UpdateResponse>(
        `${API_URL}/information/update`,
        {
          weight,
          height,
          age,
          sex,
          activity,
          goals,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      if (response.data.success) {
        console.log(response.data.content.message);
        navigate("/createrecipes");
      } else {
        console.log(response.data.content.message);
        setErrorMessage(response.data.errorMessage);
      }
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div className="min-h-screen flex items-center justify-center bg-white font-lexend">
      <main className="w-full max-w-md p-6 bg-white rounded-lg">
        <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
          Your Profile
        </h2>
        <p className="text-[#648772] text-sm font-normal mt-2">
          Update your personal information to tailor your menu.
        </p>

        {errorMessage && (
          <div className="text-red-600 text-sm font-medium pt-2">
            {errorMessage}
          </div>
        )}

        <FormInput value={weight} onChange={setWeight} label="Weight (kg)" />
        <FormInput value={height} onChange={setHeight} label="Height (cm)" />
        <FormInput value={age} onChange={setAge} label="Age" />
        <FormSelect
          value={sex}
          onChange={setSex}
          label="Sex"
          options={["", "Male", "Female"]}
        />
        <FormSelect
          value={activity}
          onChange={setActivity}
          label="Activity Level"
          options={["", "Low", "Moderate", "High"]}
        />
        <FormSelect
          value={goals}
          onChange={setGoals}
          label="Your goals"
          options={["", "Сut", "Maintenance", "Bulk"]}
        />

        <div className="flex justify-end mt-4">
          <button
            onClick={handleLogin}
            className="cursor-pointer rounded-xl bg-[#38e07b] text-[#111714] h-10 px-4 text-sm font-bold tracking-wide"
          >
            Save profile
          </button>
        </div>
      </main>
    </div>
  );
};

export default CreateProfilePage;
