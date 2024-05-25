import { getAuthToken } from "@/app/lib/auth/get-token";

export async function getUserProfileLoader() {
  const baseUrl = "http://localhost:8080";

  const url = new URL("/api/user/profile", baseUrl);

  const authToken = await getAuthToken();
  if (!authToken) return { ok: false, data: null, error: null };

  try {
    const response = await fetch(url.href, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${authToken}`,
      },
      cache: "no-cache",
    });

    const data = await response.json();
    console.log(data);

    if (data.error) return { ok: false, data: null, error: data.error };

    return { ok: true, data: data, error: null };
  } catch (error) {
    return { ok: false, data: null, error: error };
  }
}
