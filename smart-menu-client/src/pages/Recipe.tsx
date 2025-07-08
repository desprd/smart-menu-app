import React from "react";
import { Link } from "react-router-dom";

const Recipe: React.FC = () => {
  return (
    <div
      className="relative flex min-h-screen flex-col bg-white overflow-x-hidden"
      style={{ fontFamily: 'Lexend, "Noto Sans", sans-serif' }}
    >
      <main className="px-10 py-5 flex justify-center">
        <div className="max-w-[960px] w-full">
          <div className="flex gap-2 p-4 text-[#648772] text-base font-medium">
            <Link to={"/menu"}>Menu</Link>
            <span>/</span>
            <span className="text-[#111714]">Mediterranean Quinoa Salad</span>
          </div>

          <div className="p-4">
            <h1 className="text-[32px] font-bold text-[#111714] leading-tight mb-2">
              Mediterranean Quinoa Salad
            </h1>
          </div>

          <div className="p-4 flex flex-col xl:flex-row gap-4 min-h-[300px]">
            {/* Image block */}
            <div
              className="w-full xl:w-1/2 min-h-[300px] bg-cover bg-center rounded-xl"
              style={{
                backgroundImage:
                  'url("https://lh3.googleusercontent.com/aida-public/AB6AXuBIra80O-rPmFu2CODJGj3k1rneBlh9PoBbTKa3BI7oISxz8uX4d8WzpzH_40OIgNzcrV7LUxlk_WGbeHjbDMLeOBoGFN6ShfCEklHzuKTzH5-WSaKlspZBeDzy5aQIIQoxGL3jL04Uc_JSOC02CEZZG3K1RpRDbbAs0guxrneochbx3Gebrm3tVuIKboFMYIMmk41tjWjIdovKShOXSgLiOvKygy5E4bydO2CELSkgS-iEVfrAT2vCSoMVqTwh3xdUO7cCOlSCZyM")',
              }}
            ></div>
          </div>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Ingredients
            </h2>
            <ul className="grid grid-cols-[20%_1fr] gap-x-6">
              {[
                ["Quinoa", "1 cup"],
                ["Cucumber", "1 cup, diced"],
                ["Tomatoes", "1 cup, diced"],
                ["Red Onion", "1/2 cup, finely chopped"],
                ["Kalamata Olives", "1/2 cup, pitted and halved"],
                ["Feta Cheese", "1/2 cup, crumbled"],
                ["Fresh Parsley", "1/4 cup, chopped"],
                ["Lemon Juice", "2 tablespoons"],
                ["Olive Oil", "2 tablespoons"],
                ["Salt", "1/2 teaspoon"],
                ["Pepper", "1/4 teaspoon"],
              ].map(([name, quantity]) => (
                <React.Fragment key={name}>
                  <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                    {name}
                  </li>
                  <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                    {quantity}
                  </li>
                </React.Fragment>
              ))}
            </ul>
          </section>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Instructions
            </h2>
            <p className="text-base text-[#111714]">
              1. Rinse quinoa thoroughly under cold water. 2. In a medium
              saucepan, combine quinoa with 2 cups of water or vegetable broth.
              Bring to a boil, then reduce heat and simmer for 15 minutes, or
              until quinoa is cooked and water is absorbed. Fluff with a fork
              and let cool. 3. While quinoa is cooking, prepare the vegetables.
              Dice the cucumber and tomatoes, finely chop the red onion, and
              halve the Kalamata olives. 4. In a large bowl, combine the cooked
              quinoa, cucumber, tomatoes, red onion, Kalamata olives, and
              crumbled feta cheese. 5. In a small bowl, whisk together the lemon
              juice, olive oil, salt, and pepper to make the dressing. 6. Pour
              the dressing over the quinoa and vegetables. Toss gently to
              combine. 7. Garnish with fresh parsley. Serve immediately or chill
              for later.
            </p>
          </section>

          <section className="p-4">
            <h2 className="text-[22px] font-bold text-[#111714] mb-4">
              Nutritional Information
            </h2>
            <ul className="grid grid-cols-[20%_1fr] gap-x-6">
              {[
                ["Calories", "350 kcal"],
                ["Protein", "12g"],
                ["Fat", "18g"],
                ["Carbohydrates", "35g"],
              ].map(([name, value]) => (
                <React.Fragment key={name}>
                  <li className="border-t border-[#dce5df] py-3 text-sm text-[#648772]">
                    {name}
                  </li>
                  <li className="border-t border-[#dce5df] py-3 text-sm text-[#111714]">
                    {value}
                  </li>
                </React.Fragment>
              ))}
            </ul>
          </section>
        </div>
      </main>
    </div>
  );
};

export default Recipe;
