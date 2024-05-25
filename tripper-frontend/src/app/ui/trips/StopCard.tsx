"use client";

import { useEffect, useState } from "react";

import { fetchStopById } from "@/app/lib/tripsUtil";
import { StopInfo, Stop } from "@/types";

export const StopCard = ({
  time,
  stopInfo,
  i,
  showDetails,
}: {
  time: string;
  stopInfo: StopInfo;
  i: number;
  showDetails: boolean;
}) => {
  const [stop, setStop] = useState<Stop | null>(null);

  useEffect(() => {
    async function loadStop() {
      fetchStopById(stopInfo.stopId).then(setStop);
    }

    loadStop();
  }, [time, stopInfo]);

  function calculateTimeRange(startTime: string, duration: number) {
    let [hours, minutes] = startTime.split(":").map(Number);

    let startDate = new Date();
    startDate.setHours(hours);
    startDate.setMinutes(minutes);

    let endDate = new Date(startDate);
    endDate.setMinutes(startDate.getMinutes() + duration);

    let startHours = startDate.getHours().toString().padStart(2, "0");
    let startMinutes = startDate.getMinutes().toString().padStart(2, "0");
    let endHours = endDate.getHours().toString().padStart(2, "0");
    let endMinutes = endDate.getMinutes().toString().padStart(2, "0");

    return `${startHours}:${startMinutes} - ${endHours}:${endMinutes}`;
  }

  function shouldHighlight(time: string): boolean {
    const currentDate = new Date();
    const [hours, minutes] = time.split(":").map(Number);
    const timeDate = new Date();
    timeDate.setHours(hours);
    timeDate.setMinutes(minutes);

    return currentDate > timeDate;
  }

  return (
    <>
      <hr />

      {/* <div className="timeline-middle">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 20 20"
          fill="currentColor"
          className="h-5 w-5"
        >
          <path
            fillRule="evenodd"
            d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
            clipRule="evenodd"
          />
        </svg>
      </div> */}

      <div
        className={`${
          i % 2 === 0 ? "timeline-start md:text-end" : "timeline-end"
        } ${showDetails ? "mb-20" : "mb-10"} mx-5 flex flex-col space-y-4`}
      >
        <time className="text-md italic">
          {`${calculateTimeRange(time, stopInfo.duration)} (${
            stopInfo.duration
          } mins)`}
        </time>

        <h2 className="text-2xl font-extrabold">{stop?.title}</h2>
        <p className="text-md">{`📍 ${stop?.location}`}</p>
      </div>

      {showDetails && (
        <div
          className={`${
            i % 2 === 0 ? "timeline-end" : "timeline-start md:text-end"
          } ${showDetails ? "mb-20" : "mb-10"} mx-5`}
        >
          <div className="card max-w-lg bg-base-200 shadow-xl">
            <div className="card-body">
              {/* Description */}
              <h2 className="card-title text-lg">Description</h2>
              <p className="text-md">{stop?.description}</p>

              <div className="divider"></div>

              {/* Notes */}
              <h2 className="card-title text-lg">Notes</h2>
              <div className="chat chat-end">
                <div className="chat-bubble text-sm text-white">
                  {stop?.notes.map((note) => {
                    return <p key={note}>{note}</p>;
                  })}
                </div>
              </div>
            </div>
          </div>

          <div className="mt-5 flex gap-2">
            {stop?.tags.map((tag) => {
              return (
                <div
                  key={tag}
                  className="badge badge-outline font-semibold italic p-2"
                >
                  {`#${tag}`}
                </div>
              );
            })}
          </div>
        </div>
      )}

      <hr />
    </>
  );
};
