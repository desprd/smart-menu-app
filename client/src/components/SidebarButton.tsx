import React from "react";
import { Link } from "react-router-dom";
interface SidebarButtonProps {
  buttonName: string;
}

const SidebarButton: React.FC<SidebarButtonProps> = ({ buttonName }) => {
  return (
    <div>
      <Link
        to={`/${buttonName.toLowerCase()}`}
        className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-[#111714] hover:bg-[#f0f4f2] rounded-xl"
      >
        <img
          src={`/${buttonName.toLowerCase()}.png`}
          alt="Smart Menu Logo"
          className="w-6 h-6"
        />
        <div>{buttonName}</div>
      </Link>
    </div>
  );
};

export default SidebarButton;
