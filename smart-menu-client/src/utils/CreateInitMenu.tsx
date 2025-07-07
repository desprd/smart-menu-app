import axios from "axios";
import type { UpdateResponse } from "../types/update";

export const getInitMenu = async (API_URL: string, token: string) => {
  try {
    const response = await axios.get<UpdateResponse>(
      `${API_URL}/menu/generate/init`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    if (response.data.success) {
      console.log(response.data.content.message);
    } else {
      console.error(response.data.errorMessage);
    }
  } catch (error) {
    console.error(error);
  }
};
