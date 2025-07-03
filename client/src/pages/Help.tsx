import React from "react";
import Sidebar from "../components/Sidebar";

const Help: React.FC = () => {
  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />

          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="p-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Help
              </h2>
              <p className="text-[#648772] text-sm font-normal mt-2">
                Need assistance? Feel free to contact our support team.
              </p>
            </div>

            <div className="px-4 py-6">
              <div className="rounded-xl border border-[#dce5df] bg-[#f9fbfa] p-6">
                <h3 className="text-[#111714] text-lg font-bold mb-2">
                  Contact Email
                </h3>
                <p className="text-[#111714] text-base font-medium">
                  support@smartmenuapp.com
                </p>
              </div>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
};

export default Help;
