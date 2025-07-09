export type ChangeCredentialsResponse = {
  success: boolean;
  content: Credentials;
  errorMessage: string | null;
};

export type Credentials = {
  email: string;
  password: string;
};
