"use client";

export default function Page({ params }: { params: { id: string } }) {
  const id = params.id;

  return <p>Trip: {id}</p>;
}
