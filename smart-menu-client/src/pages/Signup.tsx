import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Header from "../components/Header";
import InputField from "../components/InputField";
import type { AuthServerResponse } from "../types/auth";
import axios from "axios";
import { useAuth } from "../context/ContextProvider";

const Signup: React.FC = () => {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  const API_URL = import.meta.env.VITE_API_URL;
  const { login } = useAuth();

  const handleSignup = async () => {
    if (password.length < 6) {
      setErrorMessage("Password supposed to be at least 6 characters long");
      return;
    }
    if (password != confirmPassword) {
      setErrorMessage("Failed to confirm password");
      return;
    }
    if (!email.includes("@") || !email.includes(".")) {
      setErrorMessage("Please enter a valid email address");
      return;
    }
    try {
      const response = await axios.post<AuthServerResponse>(
        `${API_URL}/auth/signup`,
        {
          username: fullName,
          email,
          password,
        }
      );
      if (response.data.success && response.data.content.token) {
        localStorage.setItem("token", response.data.content.token);
        login(response.data.content);
        navigate("/createprofile");
      } else {
        setErrorMessage(response.data.errorMessage);
      }
    } catch (error) {
      console.error("Registration error ", error);
    }
  };

  return (
    <div className="min-h-screen bg-white font-lexend text-[#111714]">
      <Header pageName="Signup" />

      <main className="flex justify-center px-4 md:px-10 py-10">
        <div className="w-full max-w-lg flex flex-col">
          <div
            className="h-40 md:h-48 w-full bg-cover bg-center rounded-none md:rounded-xl"
            style={{
              backgroundImage:
                'url("https://lh3.googleusercontent.com/aida-public/AB6AXuB0hRsLhiASxdnss89yZnurBavviOSqZ4IejZKbYauB6xMQFENfwDZnmXPmsvbI1q90yhZmXSns3IfAbtjZApFpVnWuqar1h1esMGCTBEcARLnVN-6rpg33VbHcVfabooTsPrXgP9gOkH3Ot_EMliNyDT59lNyIL2OsLggh2Lr6LxmfNL9dIN3poCwaNRyUvmkQFZ-gonXYs2P0oCz37CmfZAWtScHLdKxOwLa2G7dS0wCAJQfE0jrp6Xy3ZQKeNzA7b_96OQ_jHNA")',
            }}
          />

          <h2 className="text-2xl md:text-3xl font-bold text-center pt-6 pb-4">
            Create your account
          </h2>

          <div className="space-y-4 px-2 md:px-0">
            {errorMessage && (
              <div className="text-red-600 text-sm font-medium px-2 md:px-0 pt-2">
                {errorMessage}
              </div>
            )}
            <InputField
              placeholder="Full name"
              value={fullName}
              onChange={setFullName}
            />
            <InputField
              placeholder="Email"
              value={email}
              onChange={setEmail}
              type="email"
            />
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
              className="cursor-pointer w-full bg-[#38e07b] h-12 rounded-xl text-base font-bold tracking-wide text-[#111714] hover:opacity-90 transition"
            >
              Sign Up
            </button>
          </div>

          <div className="text-center text-sm text-[#648772] underline pt-2">
            <Link to="/login">Already have an account? Log In</Link>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Signup;
