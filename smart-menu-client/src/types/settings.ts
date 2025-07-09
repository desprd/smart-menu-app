export type MenuSettingsResponse = {
  success: boolean;
  content: MenuSettings;
  errorMessage: string | null;
};

export type MenuSettings = {
  isVegetarian: boolean;
  isGlutenFree: boolean;
  isDairyFree: boolean;
  isNutFree: boolean;
  cuisine: string;
  excluded: string;
};
