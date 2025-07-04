export type AuthServerResponse = {
  success: boolean;
  content: AuthUserData;
  errorMessage: string;
};

export type AuthUserData = {
  username: string;
  email: string;
  token?: string;
};
