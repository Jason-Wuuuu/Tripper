"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

import { fetchTrips } from "@/app/lib/data";
import { TripsResponse, Trip } from "@/types";

import Search from "@/app/ui/trips/Search";

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

  // const filteredTrips = trips.filter((trip) => trip.id !== selectedTrip?.id);

  return (
    <div className="flex h-full pt-16 font-mono">
      {/* left */}
      <div
        className={`${
          selectedTrip ? "w-1/4" : "w-4/5 mx-auto"
        } flex flex-col px-3`}
      >
        <div className="flex-grow overflow-y-auto hide-scrollbar py-5">
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
                    trip.id !== selectedTrip?.id
                      ? "bg-base-200 shadow-md cursor-pointer hover:bg-base-300"
                      : "border-2"
                  }`}
                  onClick={() => setSelectedTrip(trip)}
                >
                  <div className="card-body">
                    <h2 className="card-title text-md">{trip.title}</h2>

                    {trip.id === selectedTrip?.id ? (
                      <div className="card-actions justify-end">
                        <button
                          type="button"
                          className="btn btn-outline btn-sm shadow-md"
                          onClick={(e) => {
                            e.stopPropagation(); // This stops the event from bubbling up to the parent elements.
                            setSelectedTrip(null);
                          }}
                        >
                          Close
                        </button>
                      </div>
                    ) : (
                      <p>
                        {new Date(trip.startDate).toLocaleDateString()} -{" "}
                        {new Date(trip.endDate).toLocaleDateString()}
                      </p>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      </div>

      {/* right */}
      {selectedTrip && (
        <div className="w-3/4 bg-base-100 flex flex-col">
          <div className="flex-grow overflow-y-auto px-5">{children}</div>
        </div>
      )}
    </div>
  );
}
