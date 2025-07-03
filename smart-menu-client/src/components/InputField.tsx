import React from "react";

interface InputFieldProps {
  placeholder: string;
  type?: string;
  value: string;
  onChange: (value: string) => void;
}

const InputField: React.FC<InputFieldProps> = ({
  placeholder,
  type = "text",
  value,
  onChange,
}) => {
  return (
    <input
      type={type}
      placeholder={placeholder}
      value={value}
      onChange={(e) => onChange(e.target.value)}
      className="w-full rounded-xl bg-[#f0f4f2] text-[#111714] placeholder:text-[#648772] p-4 h-14 text-base focus:outline-none"
    />
  );
};

export default InputField;
