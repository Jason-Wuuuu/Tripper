"use client";

import { Fragment, useEffect, useState } from "react";

import { Trip } from "@/types";
import { fetchTripById } from "@/app/lib/tripsUtil";
import { TripDetailPage } from "@/app/ui/trips/TripDetailPage";

export default function Page({ params }: { params: { id: string } }) {
  const [trip, setTrip] = useState<Trip>();

  useEffect(() => {
    async function loadTrips() {
      fetchTripById(params.id).then(setTrip);
    }

    loadTrips();
  }, [params]);

  return <Fragment>{trip && <TripDetailPage trip={trip} />}</Fragment>;
}
