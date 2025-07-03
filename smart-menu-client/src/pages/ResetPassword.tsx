import React, { useState } from "react";
import { Link } from "react-router-dom";
import Header from "../components/Header";
import InputField from "../components/InputField";
const ResetPassword: React.FC = () => {
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [isUpdated, setIsUpdated] = useState(false);

  const handleSignup = () => {
    if (password.length < 6) {
      setErrorMessage("Password supposed to be at least 6 characters long");
      return;
    }
    if (password != confirmPassword) {
      setErrorMessage("Failed to confirm password");
      return;
    }
    setErrorMessage("");
    setIsUpdated(true);
  };

  return (
    <div className="min-h-screen bg-white font-lexend text-[#111714]">
      <Header pageName="Reset Password" />
      <main className="flex justify-center px-4 md:px-10 py-10">
        <div className="w-full max-w-lg flex flex-col">
          <h2 className="text-2xl md:text-3xl font-bold text-center pt-6 pb-4">
            Reset password
          </h2>

          <div className="space-y-4 px-2 md:px-0">
            {errorMessage && (
              <div className="text-red-600 text-sm font-medium px-2 md:px-0 pt-2">
                {errorMessage}
              </div>
            )}
            {isUpdated && (
              <div className="text-green-600 text-sm font-medium px-2 md:px-0 pt-2">
                Your password updated successfully
              </div>
            )}
            <InputField
              placeholder="Password"
              type="password"
              value={password}
              onChange={setPassword}
            />
            <InputField
              placeholder="Confirm Password"
              type="password"
              value={confirmPassword}
              onChange={setConfirmPassword}
            />
          </div>

          <div className="px-2 md:px-0 py-5">
            <button
              onClick={handleSignup}
              className="w-full bg-[#38e07b] h-12 rounded-xl text-base font-bold tracking-wide text-[#111714] hover:opacity-90 transition"
            >
              Reset password
            </button>
          </div>
        </div>
      </main>
    </div>
  );
};

export default ResetPassword;
