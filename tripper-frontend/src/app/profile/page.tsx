"use client";

import { useEffect, useState } from "react";
import useApi from "../lib/api";

import { UserProfile } from "@/types";

export default function Page() {
  const [profile, setProfile] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState(true);
  const { fetchWithAuth } = useApi();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await fetchWithAuth(
          "http://localhost:8080/api/user/profile"
        );
        setProfile(data);
      } catch (error) {
        console.error("Failed to fetch profile:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, [fetchWithAuth]);

  return (
    <main>
      <h1 className="mb-4 text-xl md:text-2xl">Profile</h1>
      <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-4">
        {/* <Suspense fallback={<CardsSkeleton />}>
          <CardWrapper />
        </Suspense> */}
        <h1>Username: {profile?.username}</h1>
        <h1>Email: {profile?.email}</h1>
      </div>
    </main>
  );
}
