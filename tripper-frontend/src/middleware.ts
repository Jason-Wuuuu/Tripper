import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

/*
import { getUserProfileLoader } from "@/app/lib/userUtil";

export async function middleware(request: NextRequest) {
  const token = request.cookies.get("token"); // Adjust 'token' to your cookie name
  const currentPath = request.nextUrl.pathname;

  // Redirect unauthenticated users trying to access the profile page
  if (currentPath.startsWith("/profile") && !token) {
    return NextResponse.redirect(new URL("/login", request.url));
  }

  // For authenticated users, retrieve user profile
  if (token) {
    const user = await getUserProfileLoader(); // Only call this if the user is authenticated

    // Redirect authenticated users trying to access login or register pages
    if ((currentPath === "/login" || currentPath === "/register") && user.ok) {
      return NextResponse.redirect(new URL("/profile", request.url));
    }
  }

  return NextResponse.next();
}
*/

export function middleware(req: NextRequest) {
  const token = req.cookies.get("token");

  if (!token) {
    return NextResponse.redirect("/login");
  }

  return NextResponse.next();
}

export const config = {
  matcher: ["/profile/:path*"],
};
