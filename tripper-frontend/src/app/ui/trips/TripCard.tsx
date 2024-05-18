"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

import clsx from "clsx";

import { Trip } from "@/types";

export default function TripCard({ trip }: { trip: Trip }) {
  const pathname = usePathname();

  return (
    <div className="card bg-base-100 shadow-md">
      <div className="card-body">
        <h2 className="card-title text-lg">{trip.title}</h2>
        <p>
          {new Date(trip.startDate).toLocaleDateString()} -{" "}
          {new Date(trip.endDate).toLocaleDateString()}
        </p>

        <div className="card-actions justify-end">
          <Link className="btn" href={`${pathname}/${trip.id}`}>
            View Details
          </Link>
        </div>
      </div>
    </div>
  );
}
