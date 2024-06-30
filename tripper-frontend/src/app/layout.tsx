import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

import Search from "@/app/ui/trips/Search";
import { AuthProvider } from "./context/AuthContext";

const inter = Inter({ subsets: ["latin"] });

const metadata: Metadata = {
  title: "Tripper",
  // description: "",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" data-theme="cupcake">
      <AuthProvider>
        <body className={inter.className}>
          {/* nav bar */}
          <div className="navbar bg-base-100 fixed z-50 shadow-md font-mono">
            <div className="flex-1">
              <a className="btn btn-ghost text-3xl font-extrabold" href="/">
                Tripper
              </a>
            </div>
            <div className="flex-none gap-2">
              <Search placeholder="Search Trips" />

              <button type="button" className="btn">
                Start Planning
              </button>

              <div className="dropdown dropdown-end">
                <div
                  tabIndex={0}
                  role="button"
                  className="btn btn-ghost btn-circle avatar"
                >
                  <div className="w-10 rounded-full">
                    <img
                      alt="Tailwind CSS Navbar component"
                      src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.jpg"
                    />
                  </div>
                </div>
                <ul
                  tabIndex={0}
                  className="mt-3 z-[1] p-2 shadow menu menu-sm dropdown-content bg-base-100 rounded-box w-52"
                >
                  <li>
                    <a className="justify-between" href="/profile">
                      Profile
                    </a>
                  </li>
                  <li>
                    <a>Logout</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          {/* page body */}
          <div className="h-screen overflow-hidden">{children}</div>
        </body>
      </AuthProvider>
    </html>
  );
}
