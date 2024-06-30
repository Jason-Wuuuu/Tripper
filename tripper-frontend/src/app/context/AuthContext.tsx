"use client";

// context/AuthContext.tsx
import React, { createContext, useState, useEffect, ReactNode } from "react";
import { useRouter } from "next/navigation";
import { parseCookies, setCookie, destroyCookie } from "nookies";

interface AuthContextProps {
  token: string | null;
  login: (token: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextProps | undefined>(undefined);

const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [token, setToken] = useState<string | null>(null);
  const router = useRouter();

  useEffect(() => {
    const { token } = parseCookies();
    console.log("Token from cookies:", token);

    if (token) {
      setToken(token);
    }
  }, []);

  const login = (token: string) => {
    setCookie(null, "token", token, { path: "/" });
    setToken(token);
    router.push("/profile");
  };

  const logout = () => {
    destroyCookie(null, "token");
    setToken(null);
    router.push("/login");
  };

  return (
    <AuthContext.Provider value={{ token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthProvider, AuthContext };
