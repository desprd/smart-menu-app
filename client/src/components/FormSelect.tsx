import React from "react";

interface FormSelectProps {
  label: string;
  options: string[];
}

const FormSelect: React.FC<FormSelectProps> = ({ label, options }) => {
  return (
    <div className="flex flex-wrap items-end gap-4 px-4 py-3 max-w-[480px]">
      <label className="flex flex-col min-w-40 flex-1">
        <p className="pb-2 text-base font-medium text-[#111714]">{label}</p>
        <select className=" h-14 rounded-xl bg-[#f0f4f2] p-4 text-base text-[#111714] placeholder-[#648772] focus:outline-none">
          {options.map((opt, index) => (
            <option key={index} value={opt}>
              {opt || "Select"}
            </option>
          ))}
        </select>
      </label>
    </div>
  );
};

export default FormSelect;
