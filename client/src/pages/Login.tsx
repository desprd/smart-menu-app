import React, { useState } from "react";
import InputField from "../components/InputField";

import Header from "../components/Header";
import { Link } from "react-router-dom";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleLogin = () => {
    // Placeholder logic
    alert(`Logging in with: ${email}, ${password}`);
  };

  return (
    <div className="min-h-screen bg-white font-lexend text-[#111714]">
      <Header pageName="Login" />

      <main className="flex justify-center px-6 py-10">
        <div className="w-full max-w-md">
          <div
            className="h-48 rounded-xl bg-cover bg-center"
            style={{
              backgroundImage:
                'url("https://lh3.googleusercontent.com/aida-public/AB6AXuD2aBirJeWrBwXJMHvYGQKmEBpSFWgFXcxfit5K5Ibeu6l0rXPYocPs-N1tIX-2uk_FVhy3urkwbzTziAJXC_YpOj6oKJBAx0a8x174zW3l6jUwNRA8PwQecQeM5c3spkMN5BxnPybeQ_cz0P9dKId-g6Zrr_nmeyKwW0VxUCyD284kW4wYMm4hx0CA_Y4PQmV38X2r7-pSsNQpKzX5iQsbhqeywGZXYJIphCet-BFZgH3qGjsLY047mmpJRJUaqQ3wzBgt0tjcvc0")',
            }}
          />

          <h2 className="text-2xl font-bold text-center pt-6 pb-2">
            Welcome back
          </h2>

          <div className="space-y-4 px-2 py-2">
            <InputField placeholder="Email" value={email} onChange={setEmail} />
            <InputField
              placeholder="Password"
              type="password"
              value={password}
              onChange={setPassword}
            />
          </div>

          <div className="px-2 text-sm text-[#648772] underline pt-1 pb-3 cursor-pointer">
            Forgot password?
          </div>

          <div className="px-2 py-3">
            <button
              onClick={handleLogin}
              className="w-full bg-[#38e07b] h-12 rounded-xl text-base font-bold tracking-wide text-[#111714]"
            >
              Log in
            </button>
          </div>

          <div className="text-center text-sm text-[#648772] underline pt-2">
            <Link to={"/signup"}>Don’t have an account? Sign up</Link>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Login;
