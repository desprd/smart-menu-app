import React from "react";
import SidebarButton from "./SidebarButton";

const Sidebar: React.FC = () => {
  return (
    <aside className="hidden md:flex flex-col w-80 min-h-[700px] bg-white p-4 rounded-xl ">
      <h1 className="text-[#111714] text-xl font-medium mb-4 ">Smart Menu</h1>
      <nav className="flex flex-col gap-2">
        {["Profile", "Menu", "Recipes", "Settings", "Help"].map((item) => (
          <SidebarButton buttonName={item} />
        ))}
      </nav>
    </aside>
  );
};

export default Sidebar;
