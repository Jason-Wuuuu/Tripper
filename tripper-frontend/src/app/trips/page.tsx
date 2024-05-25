"use client";

import { Fragment, useEffect, useState } from "react";

import { Trip } from "@/types";
import { fetchVisibleTrips } from "@/app/lib/tripsUtil";
import TripCard from "../ui/trips/TripCard";

export default function Trips() {
  const [trips, setTrips] = useState<Trip[]>([]);

  useEffect(() => {
    async function loadTrips() {
      fetchVisibleTrips().then(setTrips);
    }

    loadTrips();
  }, []);

  return (
    <Fragment>
      {trips?.map((trip: Trip) => (
        <TripCard key={trip.tripId} trip={trip} />
      ))}
    </Fragment>
  );
}
