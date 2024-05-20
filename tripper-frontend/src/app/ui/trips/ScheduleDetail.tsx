import { Schedule } from "@/types";

export default function ScheduleDetail({
  schedule,
  showDetails,
}: {
  schedule: Schedule;
  showDetails: boolean;
}) {
  const calculateDuration = (startTime: string, endTime: string) => {
    const start = new Date(startTime);
    const end = new Date(endTime);

    const durationMs = end.getTime() - start.getTime();
    const hours = Math.floor(durationMs / (1000 * 60 * 60));
    const minutes = Math.floor((durationMs % (1000 * 60 * 60)) / (1000 * 60));

    return ` (${hours ? `${hours}h ` : ""}${minutes}m)`;
  };

  return (
    <ul className="timeline timeline-snap-icon max-md:timeline-compact timeline-vertical">
      {schedule.stops.map((stop, i) => {
        return (
          <li key={stop.id}>
            <hr />

            <div
              className={`${
                i % 2 === 0 ? "timeline-start md:text-end" : "timeline-end"
              } ${
                showDetails ? "mb-20" : "mb-10"
              }  mx-5 flex flex-col space-y-4`}
            >
              <time className="text-md italic">
                {new Date(stop.startTime).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}{" "}
                -{" "}
                {new Date(stop.endTime).toLocaleTimeString([], {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
                {calculateDuration(stop.startTime, stop.endTime)}
              </time>
              <h2 className="text-2xl font-extrabold">{stop.title}</h2>
              <p className="text-md">{`📍 ${stop.location}`}</p>
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
                    <p className="text-md">{stop.description}</p>

                    <div className="divider"></div>

                    {/* Notes */}
                    <h2 className="card-title text-lg">Notes</h2>
                    <div className="chat chat-end">
                      <div className="chat-bubble text-sm text-white">
                        {stop.notes.map((note) => {
                          return <p key={note}>{note}</p>;
                        })}
                      </div>
                    </div>
                  </div>
                </div>

                {/* Tags */}
                <div className="mt-5 flex gap-2">
                  {stop.tags.map((tag) => {
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
          </li>
        );
      })}
    </ul>
  );
}
