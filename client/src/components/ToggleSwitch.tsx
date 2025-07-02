import React from "react";

interface ToggleSwitchProps {
  checked: boolean;
  onChange: () => void;
}

const ToggleSwitch: React.FC<ToggleSwitchProps> = ({ checked, onChange }) => {
  return (
    <label
      className={`relative flex h-[31px] w-[51px] items-center rounded-full p-0.5 cursor-pointer ${
        checked ? "justify-end bg-[#38e07b]" : "justify-start bg-[#f0f4f2]"
      }`}
      onClick={onChange}
    >
      <div
        className="h-full w-[27px] rounded-full bg-white"
        style={{
          boxShadow:
            "rgba(0, 0, 0, 0.15) 0px 3px 8px, rgba(0, 0, 0, 0.06) 0px 3px 1px",
        }}
      />
      <input
        type="checkbox"
        checked={checked}
        onChange={onChange}
        className="absolute invisible"
      />
    </label>
  );
};

export default ToggleSwitch;
