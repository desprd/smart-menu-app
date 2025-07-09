export type ProfileInformationResponse = {
  success: boolean;
  content: ProfileInformation;
  errorMessage: string | null;
};

export type ProfileInformation = {
  weight: number;
  height: number;
  age: number;
  sex: string;
  activity: string;
  goals: string;
};
