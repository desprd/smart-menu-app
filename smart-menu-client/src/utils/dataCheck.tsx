export const isBlank = (str: string): boolean => {
  return !str || /^\s*$/.test(str);
};
