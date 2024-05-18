// "use client";

import Link from "next/link";

import clsx from "clsx";

import { fetchTrips } from "@/app/lib/data";
import { TripsResponse, Trip } from "@/types";
import TripCard from "./TripCard";

// import { fetchFilteredTrips } from "@/app/lib/data";
// import { UpdateInvoice, DeleteInvoice } from "@/app/ui/invoices/buttons";
// import InvoiceStatus from "@/app/ui/invoices/status";
// import { formatDateToLocal, formatCurrency } from "@/app/lib/utils";

export default async function Trips() {
  const tripsResponse: TripsResponse = await fetchTrips();
  // const filteredTrips = await fetchFilteredTrips(query, currentPage);

  if (tripsResponse.ok && tripsResponse.data) {
    const trips = tripsResponse.data;

    return (
      <div className="space-y-4">
        {trips.map((trip: Trip) => {
          //   const LinkIcon = link.icon;
          return (
            <TripCard key={trip.id} trip={trip} />

            // <Link
            //   key={trip.id}
            //   href={`/trips/${trip.id}`}
            //   className={clsx(
            //     "flex h-[48px] grow items-center justify-center gap-2 rounded-md bg-gray-50 p-3 text-sm font-medium hover:bg-sky-100 hover:text-blue-600 md:flex-none md:justify-start md:p-2 md:px-3 mt-3",
            //     {
            //       // "bg-sky-100 text-blue-600": pathname === tripCard.href,
            //     }
            //   )}
            // >
            //   {/* <LinkIcon className="w-6" /> */}

            //   <p className="hidden md:block">{trip.title}</p>
            // </Link>
          );
        })}
      </div>
    );
  }
}
