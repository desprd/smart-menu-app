import React, { useEffect, useState, type ReactNode } from "react";
import { Navigate, useLocation } from "react-router-dom";
import type { ValidateResponse } from "../types/validate";
import axios from "axios";

interface TokenRouteProps {
  children: ReactNode;
}

const TokenRoute: React.FC<TokenRouteProps> = ({ children }) => {
  const location = useLocation();
  const API_URL = import.meta.env.VITE_API_URL;
  const searchParams = new URLSearchParams(location.search);
  const token = searchParams.get("token");

  const [validToken, setValidToken] = useState<boolean | null>(null); // null = loading

  useEffect(() => {
    const validateToken = async () => {
      if (!token) {
        setValidToken(false);
        return;
      }
      try {
        const response = await axios.post<ValidateResponse>(
          `${API_URL}/credentials/validatetoken`,
          { token }
        );
        setValidToken(response.data.success);
      } catch (error) {
        console.error(error);
        setValidToken(false);
      }
    };

    validateToken();
  }, [token]);

  // Wait until validation is finished
  if (validToken === null) {
    return <div>Validating token...</div>; // or show spinner
  }

  if (!token || !validToken) {
    return <Navigate to="/login" replace />;
  }

  return children;
};

export default TokenRoute;
