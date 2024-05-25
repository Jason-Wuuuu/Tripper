"use client";

import { usePathname } from "next/navigation";

import Trips from "./page";

export default function Layout({ children }: { children: React.ReactNode }) {
  const path = usePathname();

  return (
    <div className="flex h-full pt-16 font-mono">
      {/* left */}
      <div
        className={`flex flex-col px-3 ${
          path !== "/trips" ? "w-1/4" : "w-4/5 mx-auto"
        }`}
      >
        <div className="flex-grow overflow-y-auto hide-scrollbar py-5">
          <div
            className={`grid gap-5 ${
              path !== "/trips" ? "grid-cols-1" : "grid-cols-2 md:grid-cols-3"
            }`}
          >
            <Trips />
          </div>
        </div>
      </div>

      {/* right */}
      {path !== "/trips" && (
        <div className="w-3/4 bg-base-100 flex flex-col">
          <div className="flex-grow overflow-y-auto px-5">{children}</div>
        </div>
      )}
    </div>
  );
}
