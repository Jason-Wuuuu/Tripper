import { useState, useEffect } from "react";

import { Trip } from "@/types";
import ScheduleDetail from "./ScheduleDetail";

const TripDetail = ({ trip }: { trip: Trip }) => {
  const [selectedTab, setSelectedTab] = useState<number>(0);
  const [showDetails, setShowDetails] = useState<boolean>(true);

  useEffect(() => {
    setSelectedTab(0);
  }, [trip]);

  const formatDate = (dateString: string): string => {
    const date = new Date(dateString);
    const options: Intl.DateTimeFormatOptions = {
      month: "short",
      day: "numeric",
    };
    return date.toLocaleDateString(undefined, options);
  };

  const getDateRange = (start: string, end: string): string[] => {
    const dateArray: string[] = [];
    let currentDate = new Date(start);

    while (currentDate <= new Date(end)) {
      dateArray.push(currentDate.toISOString());
      currentDate.setDate(currentDate.getDate() + 1);
    }

    return dateArray;
  };

  const datesArray = getDateRange(trip.startDate, trip.endDate);

  const handleTabClick = (index: number) => {
    setSelectedTab(index);
  };

  const selectedSchedule = trip.schedules.find(
    (schedule) =>
      new Date(schedule.date).toISOString() === datesArray[selectedTab]
  );

  return (
    <div className="flex flex-col h-full">
      {/* title */}
      <div className="flex justify-between items-center w-full py-4">
        <h2 className="text-2xl font-bold">{trip.title}</h2>

        <div className="flex space-x-3">
          <button
            type="button"
            className="btn btn-sm btn-accent shadow-md"
            onClick={() => setShowDetails(!showDetails)}
          >
            {showDetails ? "Hide Details" : "Show Details"}
          </button>

          <button type="button" className="btn btn-sm btn-secondary shadow-md">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-6 w-6"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
              />
            </svg>
            Save
          </button>
        </div>
      </div>

      {/* Date Tabs */}
      <div role="tablist" className="tabs tabs-bordered">
        {/* <a
          key={datesArray.length}
          role="tab"
          className={`tab text-md ${
            selectedTab === datesArray.length && "tab-active font-bold"
          }`}
          onClick={() => handleTabClick(datesArray.length)}
        >
          Overview
        </a> */}

        {datesArray.map((date, index) => (
          <a
            key={index}
            role="tab"
            className={`tab text-md ${
              selectedTab === index && "tab-active font-bold"
            }`}
            onClick={() => handleTabClick(index)}
          >
            {formatDate(date)}
          </a>
        ))}
      </div>

      {/* Schedule */}
      <div className="flex-grow overflow-y-auto hide-scrollbar p-5">
        {selectedSchedule ? (
          <ScheduleDetail
            schedule={selectedSchedule}
            showDetails={showDetails}
          />
        ) : (
          <div>No schedule for this date</div>
        )}
      </div>
    </div>
  );
};

export default TripDetail;
