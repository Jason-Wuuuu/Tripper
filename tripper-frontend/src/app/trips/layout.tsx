"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

import { fetchTrips } from "@/app/lib/data";
import { TripsResponse, Trip } from "@/types";

import Search from "@/app/ui/trips/Search";
import TripDetail from "../ui/trips/TripDetail";

export default function Layout({ children }: { children: React.ReactNode }) {
  const router = useRouter();

  const [trips, setTrips] = useState<Trip[]>([]);
  const [selectedTrip, setSelectedTrip] = useState<Trip | null>(null);

  useEffect(() => {
    const loadTrips = async () => {
      const tripsResponse: TripsResponse = await fetchTrips();

      if (tripsResponse.ok && tripsResponse.data) {
        setTrips(tripsResponse.data);
      }
    };

    loadTrips();
  }, []);

  useEffect(() => {
    selectedTrip
      ? router.push(`/trips/${selectedTrip.id}`)
      : router.push(`/trips`);
  }, [selectedTrip, router]);

  return (
    <div className="flex h-screen font-mono bg-base-100 ">
      {/* left */}
      <div
        className={`${
          selectedTrip ? "w-1/4" : "w-4/5 mx-auto"
        } flex flex-col px-4`}
      >
        <div className="flex flex-col items-center py-4 space-y-2">
          <Search placeholder="Search Trips" />
        </div>

        <div className="flex-grow overflow-y-auto hide-scrollbar px-2 py-4">
          <div
            className={`grid ${
              selectedTrip ? "grid-cols-1" : "grid-cols-2 md:grid-cols-3"
            } gap-5`}
          >
            {trips.map((trip: Trip) => {
              return (
                <div
                  key={trip.id}
                  className={`card ${
                    selectedTrip?.id === trip.id
                      ? "bg-base-100"
                      : "bg-base-200 shadow-md cursor-pointer hover:bg-base-300"
                  }`}
                  onClick={() => setSelectedTrip(trip)}
                >
                  <div className="card-body">
                    <h2 className="card-title text-md">{trip.title}</h2>
                    <p>
                      {new Date(trip.startDate).toLocaleDateString()} -{" "}
                      {new Date(trip.endDate).toLocaleDateString()}
                    </p>

                    <div className="card-actions justify-end">
                      <button
                        type="button"
                        className="btn btn-primary btn-sm shadow-md"
                      >
                        Save
                      </button>
                    </div>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </div>

      {/* right */}
      {selectedTrip && <div className="w-3/4 bg-base-100">{children}</div>}
    </div>
  );
}
