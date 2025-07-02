import React from "react";

interface Props {
  label: string;
  value: string;
  onChange: (value: string) => void;
}

const FormInput: React.FC<Props> = ({ label, value, onChange }) => {
  return (
    <div className="flex flex-wrap items-end gap-4 px-4 py-3 max-w-[500px]">
      <label className="flex flex-col min-w-40 flex-1">
        <p className="pb-2 text-base font-medium text-[#111714]">{label}</p>
        <div className="flex items-stretch w-full rounded-xl">
          <input
            className="flex-1 rounded-xl bg-[#f0f4f2] h-14 p-4 text-base placeholder-[#648772] text-[#111714] focus:outline-none"
            placeholder=""
            type="text"
            value={value}
            onChange={(e) => onChange(e.target.value)}
          />
        </div>
      </label>
    </div>
  );
};

export default FormInput;
