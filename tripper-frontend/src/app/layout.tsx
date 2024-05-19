import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

import Search from "@/app/ui/trips/Search";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
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
      <body className={inter.className}>
        {/* nav bar */}

        {/* page body */}
        <div className="h-screen overflow-hidden">{children}</div>
      </body>
    </html>
  );
}
