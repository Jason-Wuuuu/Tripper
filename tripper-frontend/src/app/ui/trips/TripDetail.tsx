// "use client";

import { fetchTripById } from "@/app/lib/data";
import { TripResponse, Trip } from "@/types";

export default async function TripDetail({ id }: { id: string }) {
  const tripResponse: TripResponse = await fetchTripById(id);

  if (tripResponse.ok && tripResponse.data) {
    const trip: Trip = tripResponse.data;

    return (
      <>
        <h1>{trip.title}</h1>
      </>
    );
  }
}
