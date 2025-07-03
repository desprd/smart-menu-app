import React from "react";

interface FormTextareaProps {
  label: string;
  value: string;
  onChange: (value: string) => void;
  placeholder: string;
}

const FormTextarea: React.FC<FormTextareaProps> = ({
  label,
  value,
  onChange,
  placeholder,
}) => {
  return (
    <div className="flex flex-wrap items-end gap-4 px-4 py-3 max-w-[480px]">
      <label className="flex flex-col min-w-40 flex-1">
        <p className="pb-2 text-base font-medium text-[#111714]">{label}</p>
        <textarea
          placeholder={placeholder}
          value={value}
          onChange={(e) => onChange(e.target.value)}
          className="min-h-36 rounded-xl bg-[#f0f4f2] p-4 text-base text-[#111714] placeholder-[#648772] focus:outline-none resize-none"
        ></textarea>
      </label>
    </div>
  );
};

export default FormTextarea;
