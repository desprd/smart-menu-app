import axios from "axios";
import React, { createContext, useContext, useEffect, useState } from "react";
import type { ReactNode } from "react";
import type { AuthUserData, AuthServerResponse } from "../types/auth";

interface AuthContextType {
  username: string | null;
  email: string | null;
  login: (data: AuthUserData) => void;
  logout: () => void;
  verifyUser: () => Promise<void>;
}

interface Props {
  children: ReactNode;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<Props> = ({ children }) => {
  const API_URL = import.meta.env.VITE_API_URL;

  const [username, setUsername] = useState<string | null>(() => {
    return localStorage.getItem("username");
  });

  const [email, setEmail] = useState<string | null>(() => {
    return localStorage.getItem("email");
  });

  const [loading, setLoading] = useState<boolean>(true);

  const login = (userData: AuthUserData) => {
    setUsername(userData.username);
    setEmail(userData.email);
    localStorage.setItem("username", userData.username);
    localStorage.setItem("email", userData.email);
  };

  const logout = () => {
    setUsername(null);
    setEmail(null);
    localStorage.clear();
  };

  const verifyUser = async () => {
    try {
      const token = localStorage.getItem("token");
      if (!token) {
        logout();
        return;
      }
      const response = await axios.get<AuthServerResponse>(
        `${API_URL}/auth/verify`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      if (response.data.success) {
        login(response.data.content);
      } else {
        console.log("Failed to verify ", response.data.errorMessage);
        logout();
      }
    } catch (error) {
      console.error("Verification error:", error);
      logout();
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    verifyUser();
    console.log("Verified");
  }, []);

  if (loading) return <div>Loading secure session...</div>;

  return (
    <AuthContext.Provider
      value={{ username, email, login, logout, verifyUser }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
