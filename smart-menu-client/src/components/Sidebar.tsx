import React from "react";
import SidebarButton from "./SidebarButton";
import LogoutButton from "./LogoutButton";
import { useAuth } from "../context/ContextProvider";
import { useNavigate } from "react-router-dom";

const Sidebar: React.FC = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();
  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <aside className="hidden md:flex flex-col w-80 min-h-[700px] bg-white p-4 rounded-xl ">
      <h1 className="text-[#111714] text-xl font-medium mb-4 ">Smart Menu</h1>
      <nav className="flex flex-col gap-2">
        {["Profile", "Menu", "Recipes", "Settings", "Help"].map((item) => (
          <SidebarButton buttonName={item} />
        ))}
        <LogoutButton handleLogout={handleLogout} />
      </nav>
    </aside>
  );
};

export default Sidebar;
