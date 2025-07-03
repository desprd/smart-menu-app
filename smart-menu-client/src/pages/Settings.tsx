import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import SubmitButton from "../components/SubmitButton";
import FormInput from "../components/FormInput";

const Settings: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const handleSubmit = () => {
    if (password.length < 6) {
      setErrorMessage("Password supposed to be at least 6 characters long");
      return;
    }
    if (!email.includes("@") || !email.includes(".")) {
      setErrorMessage("Please enter a valid email address");
      return;
    }
    console.log("Changed:", { email, password });
  };
  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />
          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="p-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Settings
              </h2>
            </div>

            {/* Account Section */}
            <h3 className="text-[#111714] text-lg font-bold tracking-[-0.015em] px-4 pb-2 pt-4">
              Change credentials
            </h3>
            {errorMessage && (
              <div className="text-red-600 text-sm font-medium px-2 md:px-0 pt-2">
                {errorMessage}
              </div>
            )}
            <FormInput
              value={email}
              onChange={setEmail}
              label="Email"
              type="email"
            />
            <FormInput
              value={password}
              onChange={setPassword}
              label="Password"
              type="password"
            />
            <SubmitButton onClick={handleSubmit} label="Update Account" />
          </main>
        </div>
      </div>
    </div>
  );
};

export default Settings;
