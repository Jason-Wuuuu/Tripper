import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";
import { getUserProfileLoader } from "@/app/lib/get-user-profile-loader";

export async function middleware(request: NextRequest) {
  const user = await getUserProfileLoader();
  const currentPath = request.nextUrl.pathname;

  // Redirect unauthenticated users trying to access the profile page
  if (currentPath.startsWith("/profile") && user.ok === false) {
    return NextResponse.redirect(new URL("/login", request.url));
  }

  // Redirect authenticated users trying to access login or register pages
  if (
    (currentPath === "/login" || currentPath === "/register") &&
    user.ok === true
  ) {
    return NextResponse.redirect(new URL("/profile", request.url));
  }

  return NextResponse.next();
}
