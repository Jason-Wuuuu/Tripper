// "use client";

import Link from "next/link";

// import { fetchFilteredTrips } from "@/app/lib/data";
// import { UpdateInvoice, DeleteInvoice } from "@/app/ui/invoices/buttons";
// import InvoiceStatus from "@/app/ui/invoices/status";
// import { formatDateToLocal, formatCurrency } from "@/app/lib/utils";

import clsx from "clsx";

const tripCards = [
  {
    title: "Trip to Taipei",
    href: "/trips/1",
    // icon: HomeIcon
  },
  {
    title: "Trip to NYC",
    href: "/trips/2",
    // icon: HomeIcon
  },
  {
    title: "Trip to LA",
    href: "/trips/3",
    // icon: HomeIcon
  },
];

export default async function Trips() {
  // const trips = await fetchFilteredTrips(query, currentPage);

  return (
    <>
      {tripCards.map((tripCard) => {
        //   const LinkIcon = link.icon;
        return (
          <Link
            key={tripCard.title}
            href={tripCard.href}
            className={clsx(
              "flex h-[48px] grow items-center justify-center gap-2 rounded-md bg-gray-50 p-3 text-sm font-medium hover:bg-sky-100 hover:text-blue-600 md:flex-none md:justify-start md:p-2 md:px-3",
              {
                // "bg-sky-100 text-blue-600": pathname === tripCard.href,
              }
            )}
          >
            {/* <LinkIcon className="w-6" /> */}
            <p className="hidden md:block">{tripCard.title}</p>
          </Link>
        );
      })}
    </>
  );
}
