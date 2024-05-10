import { logoutAction } from "@/app/lib/auth/auth-actions";

export function LogoutButton() {
  return (
    <form action={logoutAction}>
      <button type="submit" className="btn btn-accent w-full">
        Logout
      </button>
    </form>
  );
}
