"use client";

import { useEffect, useState } from "react";

import { fetchScheduleById } from "@/app/lib/tripsUtil";
import { Schedule } from "@/types";
import { StopCard } from "./StopCard";

export const ScheduleDetailPage = ({
  id,
  showDetails,
}: {
  id: string;
  showDetails: boolean;
}) => {
  const [schedule, setSchedule] = useState<Schedule | null>(null);

  useEffect(() => {
    async function loadSchedule() {
      fetchScheduleById(id).then(setSchedule);
    }

    loadSchedule();
  }, [id]);

  return (
    <ul className="timeline timeline-snap-icon max-md:timeline-compact timeline-vertical">
      {schedule &&
        Object.entries(schedule.stops).map(([time, stopInfo], i) => {
          return (
            <li key={stopInfo.stopId}>
              <StopCard
                time={time}
                stopInfo={stopInfo}
                i={i}
                showDetails={showDetails}
              />
            </li>
          );
        })}
    </ul>
  );
};
