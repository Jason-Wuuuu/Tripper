"use server";

import { cookies } from "next/headers";
import { redirect } from "next/navigation";

import { z } from "zod";

import { registerUserService, loginUserService } from "./auth-service";

const schemaRegister = z.object({
  username: z.string().min(3).max(20, {
    message: "Username must be between 3 and 20 characters",
  }),
  email: z.string().email({
    message: "Please enter a valid email address",
  }),
  password: z.string().min(6).max(100, {
    message: "Password must be between 6 and 100 characters",
  }),
});

const schemaLogin = z.object({
  identifier: z.string().min(1, {
    message: "ID is required",
  }),
  password: z.string().min(1, {
    message: "Password is required",
  }),
});

const config = {
  maxAge: 60 * 60 * 24 * 7, // 1 week
  path: "/",
  domain: process.env.HOST ?? "localhost",
  httpOnly: true,
  secure: process.env.NODE_ENV === "production",
};

export async function registerUserAction(prevState: any, formData: FormData) {
  // console.log("Register User Action");

  const validatedFields = schemaRegister.safeParse({
    username: formData.get("username"),
    email: formData.get("email"),
    password: formData.get("password"),
  });

  if (!validatedFields.success) {
    return {
      ...prevState,
      serverErrors: null,
      zodErrors: validatedFields.error.flatten().fieldErrors,
    };
  }

  const responseData = await registerUserService(validatedFields.data);

  if (!responseData) {
    return {
      ...prevState,
      serverErrors: { message: "Ops! Something went wrong. Please try again." },
      zodErrors: null,
    };
  }

  if (responseData.error) {
    return {
      ...prevState,
      serverErrors: { message: responseData.error },
      zodErrors: null,
    };
  }

  redirect("/login");
}

export async function loginUserAction(prevState: any, formData: FormData) {
  // console.log("Login User Action");

  const validatedFields = schemaLogin.safeParse({
    identity: formData.get("identity"),
    password: formData.get("password"),
  });

  if (!validatedFields.success) {
    return {
      ...prevState,
      serverErrors: null,
      zodErrors: validatedFields.error.flatten().fieldErrors,
    };
  }

  const responseData = await loginUserService(validatedFields.data);

  if (!responseData) {
    return {
      ...prevState,
      serverErrors: { message: "Ops! Something went wrong. Please try again." },
      zodErrors: null,
    };
  }

  if (responseData.error) {
    return {
      ...prevState,
      serverErrors: { message: "Incorrect ID or password" },
      zodErrors: null,
    };
  }

  cookies().set("jwt", responseData.token, config);
  redirect("/profile");
}

export async function logoutAction() {
  cookies().set("jwt", "", { ...config, maxAge: 0 });
  redirect("/");
}
