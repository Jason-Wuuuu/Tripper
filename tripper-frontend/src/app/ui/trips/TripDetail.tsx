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
          <>
            {/* <button
              type="button"
              className="btn btn-sm btn-secondary shadow-xl"
              onClick={() => setShowDetails(!showDetails)}
            >
              Expand
            </button> */}

            <ScheduleDetail
              schedule={selectedSchedule}
              showDetails={showDetails}
            />
          </>
        ) : (
          <div>No schedule for this date</div>
        )}
      </div>
    </div>
  );
};

export default TripDetail;
