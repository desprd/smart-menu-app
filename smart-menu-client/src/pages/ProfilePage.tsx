import React, { useState } from "react";
import FormInput from "../components/FormInput";
import FormSelect from "../components/FormSelect";
import Sidebar from "../components/Sidebar";
import { isBlank } from "../utils/dataCheck";
const ProfilePage: React.FC = () => {
  const [weight, setWeight] = useState("");
  const [height, setHeight] = useState("");
  const [age, setAge] = useState("");
  const [sex, setSex] = useState("");
  const [activity, setActivity] = useState("");
  const [goal, setGoal] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const handleLogin = () => {
    if (
      isBlank(weight) ||
      isBlank(height) ||
      isBlank(age) ||
      isBlank(sex) ||
      isBlank(activity) ||
      isBlank(goal)
    ) {
      setErrorMessage("Fields are supposed to be not blank");
      return;
    }
    alert(
      `Logging in with: ${weight}, ${height}, ${age}, ${activity}, ${goal}`
    );
  };
  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />

          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="p-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Your Profile
              </h2>
              <p className="text-[#648772] text-sm font-normal mt-2">
                Update your personal information to tailor your menu.
              </p>
            </div>

            {errorMessage && (
              <div className="text-red-600 text-sm font-medium px-2 md:px-0 pt-2">
                {errorMessage}
              </div>
            )}

            <FormInput
              value={weight}
              onChange={setWeight}
              label="Weight (kg)"
            />
            <FormInput
              value={height}
              onChange={setHeight}
              label="Height (cm)"
            />
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
              value={goal}
              onChange={setGoal}
              label="Your goals"
              options={["", "Сut", "Maintenance", "Bulk"]}
            />

            <div className="flex px-4 py-3">
              <button
                onClick={handleLogin}
                className="cursor-pointer rounded-xl bg-[#38e07b] text-[#111714] h-10 px-4 text-sm font-bold tracking-wide"
              >
                Update Profile
              </button>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
