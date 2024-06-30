// utils/api.ts
import useAuth from "../hooks/useAuth";

const useApi = () => {
  const { token } = useAuth();
  console.log(token);

  const fetchWithAuth = async (url: string, options?: RequestInit) => {
    const headers = {
      ...options?.headers,
      Authorization: `Bearer ${token}`,
    };

    const response = await fetch(url, {
      ...options,
      headers,
    });

    if (!response.ok) {
      throw new Error("Network response was not ok");
    }

    return response.json();
  };

  return { fetchWithAuth };
};

export default useApi;
