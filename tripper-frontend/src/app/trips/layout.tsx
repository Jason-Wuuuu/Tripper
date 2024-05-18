import Search from "@/app/ui/trips/Search";
import Trips from "../ui/trips/Trips";

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex h-screen font-mono bg-base-200">
      {/* left */}
      <div className="flex flex-col w-1/4 p-2 space-y-2">
        <div className="flex flex-col w-full items-center">
          <Search placeholder="Search Trips" />
          {/* <CreateInvoice /> */}
        </div>

        <div className="flex-grow overflow-y-auto hide-scrollbar">
          <Trips />
        </div>

        <button type="button" className="btn btn-accent w-full">
          Start Planning
        </button>
      </div>

      {/* right */}
      <div className="flex-grow p-3">{children}</div>
    </div>
  );
}
