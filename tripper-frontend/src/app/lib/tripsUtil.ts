import { Trip, Schedule, Stop } from "@/types";

const BASE_URL = "http://localhost:8080";

export async function fetchVisibleTrips(): Promise<Trip[]> {
  const url = new URL("/api/trips", BASE_URL);

  const res = await fetch(url.href, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    cache: "no-cache", // 'no-store', 'no-cache'
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch trips: ${res.statusText}`);
  }

  return res.json();
}

export async function fetchTripById(id: string): Promise<Trip> {
  const url = new URL(`/api/trips/${id}`, BASE_URL);

  const res = await fetch(url.href, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    cache: "no-cache", // 'no-store', 'no-cache'
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch trip: ${res.statusText}`);
  }

  return res.json();
}

export async function fetchScheduleById(id: string): Promise<Schedule> {
  const url = new URL(`/api/schedules/${id}`, BASE_URL);

  const res = await fetch(url.href, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    cache: "no-cache", // 'no-store', 'no-cache'
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch schedule: ${res.statusText}`);
  }

  return res.json();
}

export async function fetchStopById(id: string): Promise<Stop> {
  const url = new URL(`/api/stops/${id}`, BASE_URL);

  const res = await fetch(url.href, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    cache: "no-cache", // 'no-store', 'no-cache'
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch stop: ${res.statusText}`);
  }

  return res.json();
}
