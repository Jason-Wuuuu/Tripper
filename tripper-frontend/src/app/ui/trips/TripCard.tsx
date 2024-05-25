"use client";

import { useRouter } from "next/navigation";

import { Trip } from "@/types";

export default function TripCard({ trip }: { trip: Trip }) {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/trips/${trip.tripId}`);
  };

  return (
    <div
      key={trip.tripId}
      className="card bg-base-200 shadow-md cursor-pointer hover:bg-base-300"
      onClick={handleClick}
    >
      <div className="card-body">
        <h2 className="card-title text-md">{trip.title}</h2>

        <p>{trip.description}</p>

        <p className="mt-2">
          {new Date(trip.startDate).toLocaleDateString()} -{" "}
          {new Date(trip.endDate).toLocaleDateString()}
        </p>
      </div>
    </div>
  );
}
