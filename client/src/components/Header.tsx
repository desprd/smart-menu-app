import React from "react";
import { Link } from "react-router-dom";

interface HeaderProps {
  pageName: string;
}

const Header: React.FC<HeaderProps> = ({ pageName }) => {
  return (
    <div>
      <header className=" font-lexend flex items-center justify-between border-b border-[#f0f4f2] px-10 py-3">
        <Link to="/" className="flex items-center gap-3">
          <img src="/logo.png" alt="Smart Menu Logo" className="w-8 h-8" />
          <h2 className="text-lg font-bold tracking-tight">Smart Menu</h2>
        </Link>

        {pageName === "About" && (
          <Link
            to="/login"
            className="bg-[#38e07b] text-[#111714] font-bold text-sm px-4 py-2 rounded-xl hover:opacity-90 transition"
          >
            Log in
          </Link>
        )}
      </header>
    </div>
  );
};

export default Header;
