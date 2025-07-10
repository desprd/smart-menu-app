import React from "react";

interface LogoutButtonProps {
  handleLogout: () => void;
}

const LogoutButton: React.FC<LogoutButtonProps> = ({ handleLogout }) => {
  return (
    <div>
      <button
        onClick={handleLogout}
        className="cursor-pointer flex items-center gap-3 px-3 py-2 text-sm font-medium text-[#111714] hover:bg-[#f0f4f2] rounded-xl"
      >
        <img src={"logout.png"} alt="Smart Menu Logo" className="w-6 h-6" />
        <div>Logout</div>
      </button>
    </div>
  );
};

export default LogoutButton;
