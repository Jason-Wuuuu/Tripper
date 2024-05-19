"use client";

import { useEffect, useState } from "react";

import { fetchTripById } from "@/app/lib/data";
import { TripResponse, Trip } from "@/types";

import TripDetail from "@/app/ui/trips/TripDetail";

export default function Page({ params }: { params: { id: string } }) {
  const [trip, setTrip] = useState<Trip>();

  useEffect(() => {
    const loadTrip = async () => {
      const tripResponse: TripResponse = await fetchTripById(params.id);

      if (tripResponse.ok && tripResponse.data) {
        setTrip(tripResponse.data);
      }
    };

    loadTrip();
  }, [params]);

  if (trip) {
    return <TripDetail trip={trip} />;
  }
}
