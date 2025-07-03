import React from "react";

interface SubmitButtonProps {
  label: string;
  onClick: () => void;
}

const SubmitButton: React.FC<SubmitButtonProps> = ({ label, onClick }) => {
  return (
    <div className="flex px-4 py-3  ">
      <button
        onClick={onClick}
        className="cursor-pointer rounded-xl bg-[#38e07b] text-[#111714] h-10 px-4 text-sm font-bold tracking-[0.015em]"
      >
        {label}
      </button>
    </div>
  );
};

export default SubmitButton;
