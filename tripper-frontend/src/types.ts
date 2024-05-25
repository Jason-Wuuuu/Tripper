export type Trip = {
  tripId: string;
  title: string;
  description: string;
  owner: string;
  collaborators: string[];
  visibility: boolean; // true for public, false for private
  startDate: string;
  endDate: string;
  schedules: { [index: number]: string };
  notAssignedSchedules: string[];
};

export type Schedule = {
  scheduleId: string;
  owner: string;
  collaborators: string[];
  visibility: boolean;
  stops: { [time: string]: StopInfo };
  notAssignedStops: string[];
};

export type StopInfo = {
  stopId: string;
  duration: number; // Duration in minutes
};

export type Stop = {
  stopId: string;
  owner: string;
  collaborators: string[];
  title: string;
  description: string;
  visibility: boolean;
  location: {
    type: string; // "Point" for GeoJsonPoint
    coordinates: [number, number]; // Tuple of longitude and latitude
  };
  tags: string[];
  externalLink: string;
  media: string[];
  notes: string[];
};
