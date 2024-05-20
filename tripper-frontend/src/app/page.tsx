import Link from "next/link";
import Image from "next/image";

export default function Page() {
  return (
    <main className="flex min-h-screen flex-col p-40">
      <Link href={"/trips"} className="text-3xl hover:underline">
        Trips
      </Link>
    </main>
  );
}
