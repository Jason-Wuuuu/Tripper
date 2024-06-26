"use client";

import { useFormState } from "react-dom";
import Link from "next/link";

import { loginUserAction } from "@/app/lib/auth/auth-actions";
import { ZodErrors } from "@/app/ui/errors/ZodErrors";
import { ServerErrors } from "@/app/ui/errors/ServerErrors";
import { SubmitButton } from "@/app/ui/SubmitButton";

const INITIAL_STATE = {
  data: null,
};

export function LoginForm() {
  const [formState, formAction] = useFormState(loginUserAction, INITIAL_STATE);

  return (
    <div className="w-full max-w-md">
      <form action={formAction}>
        <div className="card bg-base-100 shadow-xl">
          <div className="card-body space-y-3">
            <h2 className="card-title text-2xl font-bold">Login</h2>

            <div className="space-y-1">
              <label
                htmlFor="identifier"
                className="input input-bordered flex items-center gap-2"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  className="w-4 h-4 opacity-70"
                >
                  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
                </svg>
                <input
                  id="identifier"
                  name="identifier"
                  type="text"
                  className="grow"
                  placeholder="ID"
                  autoFocus
                />
              </label>

              <ZodErrors error={formState?.zodErrors?.identifier} />
            </div>

            <div className="space-y-1">
              <label
                htmlFor="password"
                className="input input-bordered flex items-center gap-2"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  className="w-4 h-4 opacity-70"
                >
                  <path
                    fillRule="evenodd"
                    d="M14 6a4 4 0 0 1-4.899 3.899l-1.955 1.955a.5.5 0 0 1-.353.146H5v1.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-2.293a.5.5 0 0 1 .146-.353l3.955-3.955A4 4 0 1 1 14 6Zm-4-2a.75.75 0 0 0 0 1.5.5.5 0 0 1 .5.5.75.75 0 0 0 1.5 0 2 2 0 0 0-2-2Z"
                    clipRule="evenodd"
                  />
                </svg>

                <input
                  id="password"
                  name="password"
                  type="password"
                  className="grow"
                  placeholder="Password"
                />
              </label>

              <ZodErrors error={formState?.zodErrors?.password} />
            </div>

            <div className="space-y-1">
              <div className="card-actions justify-end">
                <SubmitButton
                  text="Login"
                  loadingText="Loading"
                  className="btn-primary w-full"
                />
              </div>

              <ServerErrors error={formState?.serverErrors} />
            </div>
          </div>
        </div>

        <div className="mt-4 text-center text-sm">
          Don't have an account?
          <Link className="underline ml-2" href="register">
            Register
          </Link>
        </div>
      </form>
    </div>
  );
}
