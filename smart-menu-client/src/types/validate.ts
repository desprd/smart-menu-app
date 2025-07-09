export type ValidateResponse = {
  success: boolean;
  content: ValidateContent;
  errorMessage: string;
};

export type ValidateContent = {
  message: string;
};
