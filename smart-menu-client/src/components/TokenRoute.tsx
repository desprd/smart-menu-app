import React, { type ReactNode } from "react";
import { Navigate, useLocation } from "react-router-dom";

interface TokenRouteProps {
  children: ReactNode;
}

const TokenRoute: React.FC<TokenRouteProps> = ({ children }) => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const token = searchParams.get("token");

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  return children;
};

export default TokenRoute;
