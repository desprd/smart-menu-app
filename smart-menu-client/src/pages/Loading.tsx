import React from "react";

interface LoadingProps {
  loadingtext: string;
}

const Loading: React.FC<LoadingProps> = ({ loadingtext }) => {
  return (
    <div className="flex items-center justify-center min-h-screen bg-white font-lexend">
      <div className="text-center">
        <div className="animate-spin rounded-full h-12 w-12 border-t-4 border-[#648772] border-opacity-50 mb-4 mx-auto"></div>
        <p className="text-[#648772] text-lg font-medium">{loadingtext}</p>
      </div>
    </div>
  );
};

export default Loading;
