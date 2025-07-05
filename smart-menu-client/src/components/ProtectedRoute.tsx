import React, { type ReactNode } from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../context/ContextProvider";

interface ProtectedRouteProps {
  children: ReactNode;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const { username } = useAuth();
  if (username === null) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

export default ProtectedRoute;
