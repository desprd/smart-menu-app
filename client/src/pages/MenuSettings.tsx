import Sidebar from "../components/Sidebar";

const MenuSettings: React.FC = () => {
  return (
    <div className="relative flex min-h-screen flex-col bg-white group/design-root overflow-x-hidden font-lexend">
      <div className="layout-container flex h-full grow flex-col">
        <div className="gap-1 px-6 flex flex-1 justify-center py-5">
          <Sidebar />
          <main className="layout-content-container flex flex-col max-w-full flex-1">
            <div className="flex flex-col gap-1 px-4 py-4">
              <h2 className="text-[#111714] text-2xl sm:text-3xl font-bold">
                Menu Settings
              </h2>
              <p className="text-[#648772] text-sm font-normal leading-normal">
                Customize your weekly menu preferences to meet your dietary
                needs and taste preferences.
              </p>
            </div>

            {/* Dietary Restrictions */}
            <h3 className="text-[#111714] text-lg font-bold px-4 pb-2 pt-4">
              Dietary Restrictions
            </h3>
            <div className="px-4">
              {["Vegetarian", "Gluten-Free", "Dairy-Free", "Nut-Free"].map(
                (label) => (
                  <label key={label} className="flex gap-x-3 py-3">
                    <input
                      value={label}
                      type="checkbox"
                      className="h-5 w-5 rounded border-[#dce5df] border-2 bg-transparent text-[#38e07b] checked:bg-[#38e07b] checked:border-[#38e07b] checked:bg-[image:--checkbox-tick-svg] focus:ring-0 focus:outline-none"
                    />
                    <p className="text-[#111714] text-base font-normal">
                      {label}
                    </p>
                  </label>
                )
              )}
            </div>

            {/* Cuisine Preferences */}
            <h3 className="text-[#111714] text-lg font-bold px-4 pb-2 pt-4">
              Cuisine Preferences
            </h3>
            <div className="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
              <label className="flex flex-col min-w-40 flex-1">
                <select className="form-input h-14 p-[15px] text-base font-normal text-[#111714] bg-white border border-[#dce5df] rounded-xl focus:outline-none bg-[image:--select-button-svg]">
                  <option value="one"></option>
                  <option value="two">two</option>
                  <option value="three">three</option>
                </select>
              </label>
            </div>

            {/* Excluded Ingredients */}
            <h3 className="text-[#111714] text-lg font-bold px-4 pb-2 pt-4">
              Excluded Ingredients
            </h3>
            <div className="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
              <label className="flex flex-col min-w-40 flex-1">
                <textarea
                  placeholder="List any ingredients you want to avoid in your menu."
                  className="form-input min-h-36 p-[15px] text-base font-normal text-[#111714] bg-white border border-[#dce5df] rounded-xl focus:outline-none placeholder:text-[#648772]"
                ></textarea>
              </label>
            </div>

            {/* Submit */}
            <div className="flex px-4 py-3 justify-end">
              <button className="rounded-xl bg-[#38e07b] text-[#111714] h-10 px-4 text-sm font-bold tracking-[0.015em]">
                Save Settings
              </button>
            </div>
          </main>
        </div>
      </div>
    </div>
  );
};

export default MenuSettings;
