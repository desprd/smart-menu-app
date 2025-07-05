export type UpdateResponse = {
  success: boolean;
  content: UpdateContent;
  errorMessage: string;
};

export type UpdateContent = {
  message: string;
};
