export type Trip = {
  id: string;
  title: string;
  owners: string[]; // References to User collection documents
  visibility: boolean; // true for public, false for private
  startDate: Date; // Using JavaScript Date object
  endDate: Date;
  schedules: Schedule[]; // Embedded documents for schedules
};

export type Schedule = {
  id: string; // Unique ID for each schedule
  date: Date;
  stops: Stop[]; // Embedded documents for stops
};

export type Stop = {
  id: string; // Unique ID for each stop
  startTime: string; // Assuming time is represented as a string in HH:MM format
  endTime: string; // Assuming time is represented as a string in HH:MM format
  details: StopDetails; // Stop details are embedded for faster read access
};

export type StopDetails = {
  title: string;
  tags: string[]; // Array of strings, e.g., ["restaurant", "museum", "sight"]
  description: string;
  location: string;
  externalLink: string; // URL as a string
  media: string[]; // Array of image URLs
  notes: string[]; // Array of notes
};

export type TripsResponse = {
  ok: boolean;
  data: null | Trip[];
  error: null | Object;
};

export type TripResponse = {
  ok: boolean;
  data: null | Trip;
  error: null | Object;
};
