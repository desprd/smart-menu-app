import React from "react";

interface CheckBoxProps {
  label: string;
  checked: boolean;
  setChecked: (value: boolean) => void;
}

const CheckBox: React.FC<CheckBoxProps> = ({ label, checked, setChecked }) => {
  return (
    <div>
      <label key={label} className="flex gap-x-3 py-3">
        <input
          type="checkbox"
          checked={checked}
          onChange={() => setChecked(!checked)}
          className="h-5 w-5 rounded border-[#dce5df] border-2 bg-transparent text-[#38e07b] checked:bg-[#38e07b] checked:border-[#38e07b] checked:bg-[image:--checkbox-tick-svg] focus:ring-0 focus:outline-none"
        />
        <p className="text-[#111714] text-base font-normal">{label}</p>
      </label>
    </div>
  );
};

export default CheckBox;
