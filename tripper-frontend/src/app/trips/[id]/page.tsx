import TripDetail from "@/app/ui/trips/TripDetail";

export default function Page({ params }: { params: { id: string } }) {
  return <TripDetail id={params.id} />;
}
