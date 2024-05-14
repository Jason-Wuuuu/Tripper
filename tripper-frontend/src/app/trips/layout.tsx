import Search from "@/app/ui/trips/Search";
import Trips from "../ui/trips/Trips";

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex h-screen flex-col md:flex-row md:overflow-hidden font-mono">
      <div className="w-full flex-none md:w-64 p-3">
        <div className="flex items-center justify-between gap-2">
          <Search placeholder="Search Trips" />
          {/* <CreateInvoice /> */}
        </div>

        <Trips />
      </div>
      <div className="flex-grow p-6 md:overflow-y-auto md:p-12">{children}</div>
    </div>
  );
}
