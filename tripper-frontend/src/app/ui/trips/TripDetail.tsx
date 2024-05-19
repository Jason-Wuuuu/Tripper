// components/DateTabs.tsx
import { useState, useEffect } from "react";

import { Trip } from "@/types";
import ScheduleDetail from "./ScheduleDetail";

const TripDetail = ({ trip }: { trip: Trip }) => {
  const [selectedTab, setSelectedTab] = useState<number>(0);

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
    <div className="flex flex-col h-screen px-5">
      {/* Date Tabs */}
      <div role="tablist" className="tabs tabs-bordered pt-4">
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
          <ScheduleDetail schedule={selectedSchedule} />
        ) : (
          <div>No schedule for this date</div>
        )}
      </div>
    </div>
  );
};

export default TripDetail;
